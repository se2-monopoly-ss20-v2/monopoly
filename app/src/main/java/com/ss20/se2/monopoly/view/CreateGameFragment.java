package com.ss20.se2.monopoly.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.ss20.se2.monopoly.R;

public class CreateGameFragment extends Fragment implements View.OnClickListener{

	private Button hostBtn;
	private ImageButton backBtn;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		// Inflate the layout for this fragment
		View myView = inflater.inflate(R.layout.fragment_creategame, container, false);
		hostBtn = myView.findViewById(R.id.hostBtn);
		hostBtn.setOnClickListener(this);
		backBtn = myView.findViewById(R.id.backBtn);
		backBtn.setOnClickListener(this);
		return myView;
	}

	@Override
	public void onClick(View v){
		switch (v.getId()){
			case R.id.hostBtn:
				MainActivity.getNavController().navigate(R.id.LobbyFragment);
				break;
			case R.id.backBtn:
				MainActivity.getNavController().navigateUp();
				break;
		}
	}
}
