package com.ss20.se2.monopoly.view.deed;

import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ss20.se2.monopoly.R;
import com.ss20.se2.monopoly.models.fields.deeds.Street;

public class DeedFragment extends DialogFragment{

	private DeedViewModel viewModel;

	public DeedFragment(Street street) {
		this.viewModel = new DeedViewModel(street);
		//Empty constructor is needed for DialogFragment?
	}

	public static DeedFragment newInstance(Street street){
		return new DeedFragment(street);
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
		return inflater.inflate(R.layout.deed_fragment, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);


		TextView title = view.findViewById(R.id.textViewDeedTitle);
		title.setText(viewModel.title);

		TextView deedValue = view.findViewById(R.id.textViewDeedValue);
		deedValue.setText(getString(R.string.currencyNumber, viewModel.deedValue));

		TextView rent = view.findViewById(R.id.textViewDeedRent);
		rent.setText(getString(R.string.currencyNumber, viewModel.deedRent));

		TextView rent1House = view.findViewById(R.id.textViewDeedRent1House);
		rent1House.setText(getString(R.string.currencyNumber, viewModel.deedRent1House));

		TextView rent2Houses = view.findViewById(R.id.textViewDeedRent2Houses);
		rent2Houses.setText(getString(R.string.currencyNumber, viewModel.deedRent2Houses));

		TextView rent3Houses = view.findViewById(R.id.textViewDeedRent3Houses);
		rent3Houses.setText(getString(R.string.currencyNumber, viewModel.deedRent3Houses));

		TextView rent4Houses = view.findViewById(R.id.textViewDeedRent4Houses);
		rent4Houses.setText(getString(R.string.currencyNumber, viewModel.deedRent4Houses));

		TextView housePrice = view.findViewById(R.id.textViewHousePrice);
		housePrice.setText(getString(R.string.currencyNumber, viewModel.deedHouseCosts));

		TextView hotelPrice = view.findViewById(R.id.textViewDeedHotelPrice);
		hotelPrice.setText(getString(R.string.currencyNumber, viewModel.deedHotelCosts));

		TextView mortgage = view.findViewById(R.id.textViewDeedMortgageValue);
		mortgage.setText(getString(R.string.currencyNumber, viewModel.deedMortgage));
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
	}

	@NonNull
	@Override
	public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
		return super.onCreateDialog(savedInstanceState);
	}
}
