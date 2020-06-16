package com.ss20.se2.monopoly.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.ss20.se2.monopoly.R;

public class SettingsFragment extends Fragment implements View.OnClickListener{

	private String name = "Player";
	private Button changeBtn;
	private ImageButton backBtn;
	EditText editName;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		// Inflate the layout for this fragment
		View myView = inflater.inflate(R.layout.fragment_settings, container, false);
		editName = myView.findViewById(R.id.editText);
		editName.setHint(name);
		changeBtn = myView.findViewById(R.id.changeBtn);
		changeBtn.setOnClickListener(this);
		backBtn = myView.findViewById(R.id.backBtn);
		backBtn.setOnClickListener(this);
		return myView;
	}

	@Override
	public void onClick(View v){
		switch (v.getId()){
			case R.id.changeBtn:
				//Write the name to database
				break;
			case R.id.backBtn:
				MainActivity.getNavController().navigateUp();
				break;
			default:
				break;
		}
	}
}
