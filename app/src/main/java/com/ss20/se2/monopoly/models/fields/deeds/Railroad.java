package com.ss20.se2.monopoly.models.fields.deeds;

public class Railroad extends Deed{

	private int rent1RR;
	private int rent2RR;
	private int rent3RR;
	private int rent4RR;
	private int currentRent;
	private int initialRent;

	public Railroad(String name, int price, int mortgage){
		super(name, price, mortgage);
		currentRent = 25;
	}

	public int getRent1RR(){
		rent1RR = initialRent;
		return rent1RR;
	}

	public int getRent2RR(){
		rent2RR = initialRent * 2;
		return rent2RR;
	}

	public int getRent3RR(){
		rent3RR = initialRent * 4;
		return rent3RR;
	}

	public int getRent4RR(){
		rent4RR = initialRent * 6;
		return rent4RR;
	}

	public int getCurrentRent(){
		return currentRent;
	}
}
