package com.ss20.se2.monopoly.view.deed.railroad;

import androidx.lifecycle.ViewModel;

import com.ss20.se2.monopoly.models.fields.deeds.Railroad;

public class RailroadViewModel extends ViewModel{

	String title;
	int deedValue;
	int mortgage;
	int rent1RR;
	int rent2RR;
	int rent3RR;
	int rent4RR;

	public RailroadViewModel(Railroad railroad){
		this.title = railroad.getName();
		this.mortgage = railroad.getMortgage();
		this.deedValue = railroad.getPrice();
		this.rent1RR = railroad.getRent1RR();
		this.rent2RR = railroad.getRent2RR();
		this.rent3RR = railroad.getRent3RR();
		this.rent4RR = railroad.getRent4RR();
	}
}
