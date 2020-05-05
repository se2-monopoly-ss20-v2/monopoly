package com.ss20.se2.monopoly.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.ss20.se2.monopoly.R;

public class MainFragment extends Fragment implements View.OnClickListener{

	private Button searchBtn;
	private Button settingsBtn;
	private Button hostBtn;
	private Button oldBtn;

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
		hostBtn = myView.findViewById(R.id.hostBtn);
		hostBtn.setOnClickListener(this);
		oldBtn = myView.findViewById(R.id.buttonOldActivity);
		oldBtn.setOnClickListener(this);
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
			case R.id.hostBtn:
				MainActivity.getNavController().navigate(R.id.CreateGameFragment);
				break;
			case R.id.buttonOldActivity:
				Intent i = new Intent(getActivity(), OldActivity2.class);
				startActivity(i);
		}
	}
}
