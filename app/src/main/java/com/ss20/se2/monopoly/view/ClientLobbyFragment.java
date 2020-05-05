package com.ss20.se2.monopoly.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.ss20.se2.monopoly.R;
import com.ss20.se2.monopoly.models.Lobby;
import com.ss20.se2.monopoly.models.LobbyPlayer;
import com.ss20.se2.monopoly.models.OnLobbyDataChangedListener;
import com.ss20.se2.monopoly.network.client.GameController;
import com.ss20.se2.monopoly.network.client.JoinLobbyNetworkMessage;
import com.ss20.se2.monopoly.network.client.LeaveLobbyNetworkMessage;
import com.ss20.se2.monopoly.network.client.ReadyLobbyNetworkMessage;
import com.ss20.se2.monopoly.network.shared.RequestType;

public class ClientLobbyFragment extends Fragment implements View.OnClickListener{

	private Button readyBtn;
	private ImageButton backBtn;
	private TextView partnerTxt;
	private FragmentActivity activity;
	private OnLobbyDataChangedListener onLobbyDataChangedListener;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View myView = inflater.inflate(R.layout.fragment_clientlobby, container, false);
		readyBtn = myView.findViewById(R.id.readyBtn);
		readyBtn.setOnClickListener(this);
		backBtn = myView.findViewById(R.id.backBtn);
		backBtn.setOnClickListener(this);
		partnerTxt = myView.findViewById(R.id.clientPartnersTxt);
		activity = getActivity();
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
		switch (v.getId()){
			case R.id.readyBtn:
				ReadyLobbyNetworkMessage readymessage = new ReadyLobbyNetworkMessage();
				readymessage.setSenderName(Lobby.getInstance().getSelf().getName());
				readymessage.setSenderAddress(Lobby.getInstance().getSelf().getAddress());
				readymessage.setSenderPort(Lobby.getInstance().getSelf().getPort());
				readymessage.setType(RequestType.JOIN_GAME);
				GameController.getInstance().changeReadyLobby(readymessage);
				break;
			case R.id.backBtn:
				LeaveLobbyNetworkMessage message = new LeaveLobbyNetworkMessage();
				message.setSenderName(Lobby.getInstance().getSelf().getName());
				message.setSenderAddress(Lobby.getInstance().getSelf().getAddress());
				message.setSenderPort(Lobby.getInstance().getSelf().getPort());
				message.setType(RequestType.JOIN_GAME);
				GameController.getInstance().leaveLobby(message);
				Lobby.getInstance().unsubscribe(onLobbyDataChangedListener);
				GameController.getInstance().leaveGame();
				MainActivity.getNavController().navigateUp();
				break;
		}
	}

	public void repaintPartners(){
		activity.runOnUiThread(new Runnable(){
			@Override
			public void run(){
				partnerTxt.setText("");
				String out = "";
				for (LobbyPlayer player : Lobby.getInstance().getPlayers()){
					out = out + player.getName() + " (" + player.getGamePiece().getName() + ") ";
					if (player.isHost()){
						out = out + " [Host]";
					}else{
						if (player.isReady()){
							out = out + " [Ready]";
						}else{
							out = out + " [Not Ready]";
						}
					}
					out = out + "\n\n";
				}
				partnerTxt.setText(out);
			}
		});
	}
}
