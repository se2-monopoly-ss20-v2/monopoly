package com.ss20.se2.monopoly.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.ss20.se2.monopoly.R;
import com.ss20.se2.monopoly.models.Lobby;
import com.ss20.se2.monopoly.models.LobbyPlayer;
import com.ss20.se2.monopoly.models.OnLobbyDataChangedListener;
import com.ss20.se2.monopoly.network.LocalGamePublisher;
import com.ss20.se2.monopoly.network.NetworkUtilities;
import com.ss20.se2.monopoly.network.server.GameServer;
import com.ss20.se2.monopoly.network.server.GameServerNotRunningException;

import java.util.LinkedList;

public class HostLobbyFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener{

	private Button hostBtn;
	private TextView partnerTxt;
	private FragmentActivity activity;
	private OnLobbyDataChangedListener onLobbyDataChangedListener;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		// Inflate the layout for this fragment
		View myView = inflater.inflate(R.layout.fragment_hostlobby, container, false);
		hostBtn = myView.findViewById(R.id.startGameBtn);
		hostBtn.setOnClickListener(this);
		ImageButton backBtn = myView.findViewById(R.id.backBtn);
		backBtn.setOnClickListener(this);
		partnerTxt = myView.findViewById(R.id.partnersTxtHost);
		activity = getActivity();
		Spinner gamePieceSpinner = (Spinner) myView.findViewById(R.id.gamePieceSpinnerHost);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(activity, R.array.gamePieceArray, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		gamePieceSpinner.setAdapter(adapter);
		gamePieceSpinner.setOnItemSelectedListener(this);
		repaintPartners();
		onLobbyDataChangedListener = new OnLobbyDataChangedListener(){
			@Override
			public void onLobbyDataChanged(final Lobby lobby){
				repaintPartners();
				activity.runOnUiThread(new Runnable(){
					@Override
					public void run(){
						hostBtn.setEnabled(lobby.isReady());
					}
				});
			}
		};
		Lobby.getInstance().subscribe(onLobbyDataChangedListener);
		return myView;
	}

	@Override
	public void onClick(View v){
		int id = v.getId();
		if (id == R.id.startGameBtn){
			try{
				GameServer.getInstance().startGame(activity.getBaseContext());
			}catch (GameServerNotRunningException e){
				Log.e(NetworkUtilities.TAG, e.getMessage());
			}
			MainActivity.getNavController().navigate(R.id.GameFragment);
			Intent intent = new Intent(activity, OldActivity2.class);
			startActivity(intent);
		}else if (id == R.id.backBtn){
			MainActivity.getNavController().navigateUp();
			try{
				GameServer.getInstance().shutdownServer(v.getContext());
				LocalGamePublisher.getInstance().hideGameInNetwork(this.getContext());
				Lobby.getInstance().unsubscribe(onLobbyDataChangedListener);
				Lobby.getInstance().setPlayers(new LinkedList<LobbyPlayer>());
				Lobby.getInstance().setSelf(null);
				Lobby.getInstance().setReady(false);
				Lobby.getInstance().closeLobby();
				Lobby.getInstance().setReady(false);
				MainActivity.getNavController().navigateUp();
			}catch (Exception e){
				Log.e(NetworkUtilities.TAG, e.getMessage());
			}
		}
	}

	public void repaintPartners(){
		activity.runOnUiThread(new Runnable(){
			@Override
			public void run(){
				partnerTxt.setText("");
				String out = "";
				for (LobbyPlayer player : Lobby.getInstance().getPlayers()){
					out = String.format("%s%s (%s)", out, player.getName(), player.getGamePiece().getName());
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
		if (adapterView.getId() == R.id.gamePieceSpinnerHost){
			GameServer.getInstance().changeGamePiece(adapterView.getItemAtPosition(i).toString());
			Log.d(NetworkUtilities.TAG, adapterView.getItemAtPosition(i).toString());
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> adapterView){
		// no actions needed
	}
}
