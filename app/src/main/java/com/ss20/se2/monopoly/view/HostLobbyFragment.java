package com.ss20.se2.monopoly.view;

import android.os.Bundle;
import android.util.Log;
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
import com.ss20.se2.monopoly.network.LocalGamePublisher;
import com.ss20.se2.monopoly.network.NetworkUtilities;
import com.ss20.se2.monopoly.network.server.GameServer;

import java.util.LinkedList;

public class HostLobbyFragment extends Fragment implements View.OnClickListener{

	private Button hostBtn;
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
		// Inflate the layout for this fragment
		View myView = inflater.inflate(R.layout.fragment_hostlobby, container, false);
		hostBtn = myView.findViewById(R.id.startGameBtn);
		hostBtn.setOnClickListener(this);
		backBtn = myView.findViewById(R.id.backBtn);
		backBtn.setOnClickListener(this);
		partnerTxt = myView.findViewById(R.id.partnersTxtHost);
		activity = getActivity();
		repaintPartners();
		onLobbyDataChangedListener = new OnLobbyDataChangedListener(){
			@Override
			public void onLobbyDataChanged(Lobby lobby){
				repaintPartners();
			}
		};
		Lobby.getInstance().subscribe(onLobbyDataChangedListener);
		return myView;
	}

	@Override
	public void onClick(View v){
		switch (v.getId()){
			case R.id.startGameBtn:
				MainActivity.getNavController().navigate(R.id.GameFragment);
				break;
			case R.id.backBtn:
				MainActivity.getNavController().navigateUp();
				try{
					GameServer.getInstance().shutdownServer(v.getContext());
					LocalGamePublisher.getInstance().hideGameInNetwork(this.getContext());
					Lobby.getInstance().unsubscribe(onLobbyDataChangedListener);
					Lobby.getInstance().setPlayers(new LinkedList<LobbyPlayer>());
					Lobby.getInstance().setSelf(null);
					Lobby.getInstance().setReady(false);
					Lobby.getInstance().closeLobby();
					MainActivity.getNavController().navigateUp();
				}catch (Exception e){
					Log.e(NetworkUtilities.TAG, e.getMessage());
				}
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
					out = out + player.getName() + " (" + player.getAddress() + ":" + player.getPort() + ") " + player.getGamePiece().getName();
					if(player.isReady()){
						out = out + " Ready";
					}else{
						out = out + " Not Ready";
					}
					out = out + "\n\n";
				}
				partnerTxt.setText(out);
			}
		});
	}
}
