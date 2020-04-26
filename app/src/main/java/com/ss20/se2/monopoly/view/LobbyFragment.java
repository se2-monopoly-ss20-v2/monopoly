package com.ss20.se2.monopoly.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.ss20.se2.monopoly.R;

public class LobbyFragment extends Fragment implements View.OnClickListener{

	Button startBtn;
	private ImageButton backBtn;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View myView = inflater.inflate(R.layout.fragment_lobby, container, false);
		startBtn = myView.findViewById(R.id.startBtn);
		startBtn.setOnClickListener(this);
		backBtn = myView.findViewById(R.id.backBtn);
		backBtn.setOnClickListener(this);
		return myView;
	}

	@Override
	public void onClick(View v){
		switch (v.getId()){
			case R.id.startBtn:
				MainActivity.getNavController().navigate(R.id.GameFragment);
				break;
			case R.id.backBtn:
				MainActivity.getNavController().navigateUp();
				break;
		}
	}
}
