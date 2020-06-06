package com.ss20.se2.monopoly.view.deed.utility;

import androidx.lifecycle.ViewModel;

import com.ss20.se2.monopoly.models.fields.deeds.Utility;
import com.ss20.se2.monopoly.models.fields.deeds.UtilityType;

public class UtilityViewModel extends ViewModel{

	String title;
	UtilityType type;
	int mortgage;
	int deedValue;


	public UtilityViewModel(Utility utility){
		this.title = utility.getName();
		this.type = utility.getType();
		this.mortgage = utility.getMortgage();
		this.deedValue = utility.getPrice();
	}
}
