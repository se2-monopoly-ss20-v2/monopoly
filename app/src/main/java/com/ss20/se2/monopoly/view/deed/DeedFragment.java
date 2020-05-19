package com.ss20.se2.monopoly.view.deed;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ss20.se2.monopoly.R;
import com.ss20.se2.monopoly.models.fields.deeds.Street;

public class DeedFragment extends DialogFragment{

	private DeedViewModel viewModel;
	private TextView title;
	private TextView deedValue;
	private TextView rent;
	private TextView rent1House;
	private TextView rent2Houses;
	private TextView rent3Houses;
	private TextView rent4Houses;
	private TextView housePrice;
	private TextView hotelPrice;
	private TextView mortgage;
	private LinearLayout header;

	public DeedFragment() {
		//Empty because Android needs an constructor without args.
	}

	public static DeedFragment newInstance(){
		return new DeedFragment();
	}

	public void createViewModel(Street street) {
		this.viewModel = new DeedViewModel(street);

	}
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
		return inflater.inflate(R.layout.deed_fragment, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);

		this.title = view.findViewById(R.id.textViewDeedTitle);
		this.header = view.findViewById(R.id.deedHeader);
		this.deedValue = view.findViewById(R.id.textViewDeedValue);
		this.rent = view.findViewById(R.id.textViewDeedRent);
		this.rent1House = view.findViewById(R.id.textViewDeedRent1House);
		this.rent2Houses = view.findViewById(R.id.textViewDeedRent2Houses);
		this.rent3Houses = view.findViewById(R.id.textViewDeedRent3Houses);
		this.rent4Houses = view.findViewById(R.id.textViewDeedRent4Houses);
		this.housePrice = view.findViewById(R.id.textViewHousePrice);
		this.hotelPrice = view.findViewById(R.id.textViewDeedHotelPrice);
		this.mortgage = view.findViewById(R.id.textViewDeedMortgageValue);

		updateUI();
	}

	public void updateUI() {
		title.setText(viewModel.title);

		header.setBackgroundColor(ContextCompat.getColor(getContext(), viewModel.color));
		deedValue.setText(getString(R.string.currencyNumber, viewModel.deedValue));
		rent.setText(getString(R.string.currencyNumber, viewModel.deedRent));
		rent1House.setText(getString(R.string.currencyNumber, viewModel.deedRent1House));
		rent2Houses.setText(getString(R.string.currencyNumber, viewModel.deedRent2Houses));
		rent3Houses.setText(getString(R.string.currencyNumber, viewModel.deedRent3Houses));
		rent4Houses.setText(getString(R.string.currencyNumber, viewModel.deedRent4Houses));
		housePrice.setText(getString(R.string.currencyNumber, viewModel.deedHouseCosts));
		hotelPrice.setText(getString(R.string.currencyNumber, viewModel.deedHotelCosts));
		mortgage.setText(getString(R.string.currencyNumber, viewModel.deedMortgage));
	}
}
