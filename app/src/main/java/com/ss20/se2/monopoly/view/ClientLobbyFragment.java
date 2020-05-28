package com.ss20.se2.monopoly.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.ss20.se2.monopoly.R;
import com.ss20.se2.monopoly.models.GamePiece;
import com.ss20.se2.monopoly.models.Lobby;
import com.ss20.se2.monopoly.models.LobbyPlayer;
import com.ss20.se2.monopoly.models.OnLobbyDataChangedListener;
import com.ss20.se2.monopoly.network.NetworkUtilities;
import com.ss20.se2.monopoly.network.client.ChangeGamePieceNetworkMessage;
import com.ss20.se2.monopoly.network.client.GameController;
import com.ss20.se2.monopoly.network.client.JoinLobbyNetworkMessage;
import com.ss20.se2.monopoly.network.client.LeaveLobbyNetworkMessage;
import com.ss20.se2.monopoly.network.client.ReadyLobbyNetworkMessage;
import com.ss20.se2.monopoly.network.shared.RequestType;
import com.ss20.se2.monopoly.view.CustomElements.SpinnerAdapter;

public class ClientLobbyFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener{

	private TextView partnerTxt;
	private FragmentActivity activity;
	private OnLobbyDataChangedListener onLobbyDataChangedListener;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View myView = inflater.inflate(R.layout.fragment_clientlobby, container, false);
		Button readyBtn = myView.findViewById(R.id.readyBtn);
		readyBtn.setOnClickListener(this);
		ImageButton backBtn = myView.findViewById(R.id.backBtn);
		backBtn.setOnClickListener(this);
		partnerTxt = myView.findViewById(R.id.clientPartnersTxt);
		activity = getActivity();
		String[] piecesStringArray = getResources().getStringArray(R.array.gamePieceArray);
		Integer[] piecesImageArray = {R.drawable.boat, R.drawable.car, R.drawable.cat, R.drawable.dino, R.drawable.dog, R.drawable.duck, R.drawable.hat, R.drawable.penguin};
		Spinner gamePieceSpinner = (Spinner) myView.findViewById(R.id.gamePieceSpinnerClient);
		SpinnerAdapter adapter = new SpinnerAdapter(activity.getBaseContext(), R.layout.spinner_value_layout, piecesStringArray, piecesImageArray);
		gamePieceSpinner.setAdapter(adapter);
		gamePieceSpinner.setOnItemSelectedListener(this);
		repaintPartners();
		onLobbyDataChangedListener = new OnLobbyDataChangedListener(){
			@Override
			public void onLobbyDataChanged(Lobby lobby){
				if (lobby.isActive()){
					repaintPartners();
				}else{
					Lobby.getInstance().unsubscribe(onLobbyDataChangedListener);
					GameController.getInstance().leaveGame();
					MainActivity.getNavController().navigateUp();
				}
				if (lobby.isStarted()){
					Lobby.getInstance().unsubscribe(onLobbyDataChangedListener);
					MainActivity.getNavController().navigate(R.id.GameFragment);
					Intent intent = new Intent(activity, GameboardActivity.class);
					startActivity(intent);
				}
			}
		};
		Lobby.getInstance().subscribe(onLobbyDataChangedListener);
		JoinLobbyNetworkMessage message = new JoinLobbyNetworkMessage();
		message.setSenderName(Lobby.getInstance().getSelf().getName());
		message.setSenderAddress(Lobby.getInstance().getSelf().getAddress());
		message.setSenderPort(Lobby.getInstance().getSelf().getPort());
		message.setType(RequestType.JOIN_GAME);
		message.setGamePiece(Lobby.getInstance().getSelf().getGamePiece());
		GameController.getInstance().joinLobby(message);
		return myView;
	}

	@Override
	public void onClick(View v){
		int id = v.getId();
		if (id == R.id.readyBtn){
			ReadyLobbyNetworkMessage readymessage = new ReadyLobbyNetworkMessage();
			readymessage.setSenderName(Lobby.getInstance().getSelf().getName());
			readymessage.setSenderAddress(Lobby.getInstance().getSelf().getAddress());
			readymessage.setSenderPort(Lobby.getInstance().getSelf().getPort());
			readymessage.setType(RequestType.JOIN_GAME);
			GameController.getInstance().changeReadyLobby(readymessage);
		}else if (id == R.id.backBtn){
			LeaveLobbyNetworkMessage message = new LeaveLobbyNetworkMessage();
			message.setSenderName(Lobby.getInstance().getSelf().getName());
			message.setSenderAddress(Lobby.getInstance().getSelf().getAddress());
			message.setSenderPort(Lobby.getInstance().getSelf().getPort());
			message.setType(RequestType.LEAVE_GAME);
			GameController.getInstance().leaveLobby(message);
			Lobby.getInstance().unsubscribe(onLobbyDataChangedListener);
			GameController.getInstance().leaveGame();
			MainActivity.getNavController().navigateUp();
		}
	}

	public void repaintPartners(){
		activity.runOnUiThread(new Runnable(){
			@Override
			public void run(){
				partnerTxt.setText("");
				String out = "";
				for (LobbyPlayer player : Lobby.getInstance().getPlayers()){
					out = String.format("%s%s (%s) ", out, player.getName(), player.getGamePiece().getName());
					if (player.isHost()){
						out = String.format("%s [Host]", out);
					}else{
						if (player.isReady()){
							out = String.format("%s [Ready]", out);
						}else{
							out = String.format("%s [Not Ready]", out);
						}
					}
					out = String.format("%s%n%n", out);
				}
				partnerTxt.setText(out);
			}
		});
	}

	@Override
	public void onItemSelected(AdapterView<?> adapterView, View v, int i, long l){
		if (adapterView.getId() == R.id.gamePieceSpinnerClient){
			ChangeGamePieceNetworkMessage changeGamePieceNetworkMessage = new ChangeGamePieceNetworkMessage();
			changeGamePieceNetworkMessage.setSenderName(Lobby.getInstance().getSelf().getName());
			changeGamePieceNetworkMessage.setSenderAddress(Lobby.getInstance().getSelf().getAddress());
			changeGamePieceNetworkMessage.setSenderPort(Lobby.getInstance().getSelf().getPort());
			changeGamePieceNetworkMessage.setType(RequestType.CHANGE_GAME_PIECE);
			changeGamePieceNetworkMessage.setGamePiece(new GamePiece(adapterView.getItemAtPosition(i).toString()));
			GameController.getInstance().changeGamePiece(changeGamePieceNetworkMessage);
			Log.d(NetworkUtilities.TAG, adapterView.getItemAtPosition(i).toString());
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> adapterView){
		// no actions needed
	}
}
