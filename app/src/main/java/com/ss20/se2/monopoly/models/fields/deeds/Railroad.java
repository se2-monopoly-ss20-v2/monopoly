package com.ss20.se2.monopoly.models.fields.deeds;

public class Railroad extends Deed{

	private int rent1RR;
	private int rent2RR;
	private int rent3RR;
	private int rent4RR;

	public Railroad(String name, int price, int mortgage){
		super(name, price, mortgage);

		this.rent1RR = 25;
		this.rent2RR = rent1RR * 2;
		this.rent3RR = rent2RR * 2;
		this.rent4RR = rent3RR * 2;
	}

	public int getRent1RR(){
		return rent1RR;
	}

	public int getRent2RR(){
		return rent2RR;
	}

	public int getRent3RR(){
		return rent3RR;
	}

	public int getRent4RR(){
		return rent4RR;
	}
}
