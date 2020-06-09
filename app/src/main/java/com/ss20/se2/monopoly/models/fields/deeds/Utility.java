package com.ss20.se2.monopoly.models.fields.deeds;

public class Utility extends Deed{

	private UtilityType utilityType;

	public Utility(String name, int price, int mortgage, UtilityType type){
		super(name, price, mortgage);

		this.utilityType = type;
	}

	public UtilityType getType(){
		return utilityType;
	}

	public void setType(UtilityType type){
		this.utilityType = type;
	}
}
