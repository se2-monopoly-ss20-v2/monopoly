package com.ss20.se2.monopoly.models.fields.deeds;

public class Railroad extends Deed{

	private int currentRent;
	private int initialRent;

	public Railroad(String name, int price, int mortgage, boolean isMortgaged){
		super(name, price, mortgage, isMortgaged);
		currentRent = 25;
		initialRent = 25;
	}

	public int getRent1RR(){
		return initialRent;
	}

	public int getRent2RR(){
		return initialRent * 2;
	}

	public int getRent3RR(){
		return initialRent * 4;
	}

	public int getRent4RR(){
		return initialRent * 8;
	}

	public int getCurrentRent(){
		return currentRent;
	}

	public int getRentRelativeTo(int count) {
		switch (count) {
			case 1:
				return this.getRent1RR();
			case 2:
				return this.getRent2RR();
			case 3:
				return this.getRent3RR();
			case 4:
				return this.getRent4RR();

			default:
				return 0;
		}
	}
}
