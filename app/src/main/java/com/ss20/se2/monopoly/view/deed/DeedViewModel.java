package com.ss20.se2.monopoly.view.deed;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.ss20.se2.monopoly.models.fields.deeds.Street;

public class DeedViewModel extends ViewModel{
	// TODO: Implement the ViewModel
	String title;
	int deedValue;
	int deedRent;
	int deedRent1House;
	int deedRent2Houses;
	int deedRent3Houses;
	int deedRent4Houses;
	int deedHouseCosts;
	int deedHotelCosts;
	int deedMortgage;

	public DeedViewModel(Street street){
		this.title = street.getName();
		this.deedValue = street.getPrice();

		double rent = street.getPrice() * 0.1;
		this.deedRent = (int) rent;
		this.deedRent1House = getRentRelativeToHouseCount(1);
		this.deedRent2Houses = getRentRelativeToHouseCount(2);
		this.deedRent3Houses = getRentRelativeToHouseCount(3);
		this.deedRent4Houses = getRentRelativeToHouseCount(4);
		this.deedHouseCosts = street.getHousePrice();
		this.deedHotelCosts = street.getHotelPrice();
		this.deedMortgage = street.getMortgage();
	}


	private int getRentRelativeToHouseCount(int count) {
		switch (count) {
			case 1: return this.deedRent * 5;
			case 2: return this.deedRent * 3 * 5;
			case 3: return (int)(this.deedRent * 2.5 * 3 * 5);
			case 4: return (int)(this.deedRent * 1.25 * 2.5 * 3 * 5);
			default: return this.deedRent;
		}
	}
}
