package com.ss20.se2.monopoly.view.dialog;

import androidx.lifecycle.ViewModel;

import com.ss20.se2.monopoly.models.fields.deeds.Street;

public class DialogContainerViewModel extends ViewModel{

	private Street street;

	public DialogContainerViewModel(Street street){
		this.street = street;
	}

	public Street getStreet(){
		return street;
	}

	public void delegateAcquiring() {

	}
}
