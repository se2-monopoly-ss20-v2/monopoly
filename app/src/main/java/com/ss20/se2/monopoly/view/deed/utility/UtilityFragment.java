package com.ss20.se2.monopoly.view.deed.utility;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ss20.se2.monopoly.R;
import com.ss20.se2.monopoly.models.fields.deeds.Utility;

public class UtilityFragment extends Fragment{

	private UtilityViewModel viewModel;
	private TextView title;
	private TextView deedValue;
	private TextView specificInfo;
	private TextView mortgage;
	private ImageView imageView;

	public static UtilityFragment newInstance(){
		return new UtilityFragment();
	}

	public void createViewModel(Utility utility){
		this.viewModel = new UtilityViewModel(utility);
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
		return inflater.inflate(R.layout.utility_fragment, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);

		title = view.findViewById(R.id.textViewUtilityTitle);
		deedValue = view.findViewById(R.id.textViewUtilityDeedValue);
		specificInfo = view.findViewById(R.id.textViewUtilityInfo);
		mortgage = view.findViewById(R.id.textViewUtilityMortgage);
		imageView = view.findViewById(R.id.imageViewUtility);
		updateUI();

	}

	void updateUI() {
		title.setText(viewModel.title);
		deedValue.setText(getString(R.string.currencyNumber, viewModel.deedValue));
		mortgage.setText(getString(R.string.currencyNumber, viewModel.mortgage));

		switch (viewModel.type) {
			case WATER_WORKS:
				specificInfo.setText(getString(R.string.waterInfo));
				imageView.setImageResource(R.drawable.water_works);
				break;
			case ELECTRIC_COMPANY:
				specificInfo.setText(getString(R.string.electricInfo));
				imageView.setImageResource(R.drawable.electric);
				break;
			default:
				break;
		}

	}
}
