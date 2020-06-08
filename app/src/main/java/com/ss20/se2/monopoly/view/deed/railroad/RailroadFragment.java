package com.ss20.se2.monopoly.view.deed.railroad;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ss20.se2.monopoly.R;
import com.ss20.se2.monopoly.models.fields.deeds.Railroad;

public class RailroadFragment extends Fragment{

	private RailroadViewModel viewModel;
	private TextView title;
	private TextView deedValue;
	private TextView rent1RR;
	private TextView rent2RR;
	private TextView rent3RR;
	private TextView rent4RR;
	private TextView mortgage;

	public static RailroadFragment newInstance(){
		return new RailroadFragment();
	}

	public void createViewModel(Railroad railroad) {
		this.viewModel = new RailroadViewModel(railroad);
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
		return inflater.inflate(R.layout.railroad_fragment, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);

		this.title = view.findViewById(R.id.textViewRRTitle);
		this.deedValue = view.findViewById(R.id.textViewRRDeedValue);
		this.rent1RR = view.findViewById(R.id.textView1RR);
		this.rent2RR = view.findViewById(R.id.textView2RR);
		this.rent3RR = view.findViewById(R.id.textView3RR);
		this.rent4RR = view.findViewById(R.id.textView4RR);
		this.mortgage = view.findViewById(R.id.textViewRRMortgage);

		updateUI();
	}

	void updateUI() {
		title.setText(viewModel.title);

		deedValue.setText(getString(R.string.currencyNumber, viewModel.deedValue));
		rent1RR.setText(getString(R.string.currencyNumber, viewModel.rent1RR));
		rent2RR.setText(getString(R.string.currencyNumber, viewModel.rent2RR));
		rent3RR.setText(getString(R.string.currencyNumber, viewModel.rent3RR));
		rent4RR.setText(getString(R.string.currencyNumber, viewModel.rent4RR));
		mortgage.setText(getString(R.string.currencyNumber, viewModel.mortgage));
	}
}
