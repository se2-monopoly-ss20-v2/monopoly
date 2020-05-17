package com.ss20.se2.monopoly.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.ss20.se2.monopoly.R;
import com.ss20.se2.monopoly.models.Lobby;
import com.ss20.se2.monopoly.network.LocalGamePublisher;
import com.ss20.se2.monopoly.network.NetworkUtilities;
import com.ss20.se2.monopoly.network.server.GameServer;

public class MainFragment extends Fragment implements View.OnClickListener{

	private Button searchBtn;
	private Button settingsBtn;
	private Button hostBtn;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View myView = inflater.inflate(R.layout.fragment_main, container, false);
		searchBtn = myView.findViewById(R.id.searchBtn);
		searchBtn.setOnClickListener(this);
		settingsBtn = myView.findViewById(R.id.settingsBtn);
		settingsBtn.setOnClickListener(this);
		hostBtn = myView.findViewById(R.id.hostGameBtn);
		hostBtn.setOnClickListener(this);
		return myView;
	}

	@Override
	public void onClick(View v){
		switch (v.getId()){
			case R.id.searchBtn:
				MainActivity.getNavController().navigate(R.id.SearchFragment);
				break;
			case R.id.settingsBtn:
				MainActivity.getNavController().navigate(R.id.SettingsFragment);
				break;
			case R.id.hostGameBtn:
				MainActivity.getNavController().navigate(R.id.HostLobbyFragment);
				try{
					GameServer.getInstance().startServer();
					GameServer.getInstance().allowJoining(this.getContext());
					LocalGamePublisher.getInstance().showGameInNetwork(this.getContext(), GameServer.getInstance().getPort());
					Lobby.getInstance().addSelf(GameServer.getInstance());
					Lobby.getInstance().openLobby();
				}catch (Exception e){
					Log.e(NetworkUtilities.TAG, e.getMessage());
				}
				break;
		}
	}
}
