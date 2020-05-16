package com.ss20.se2.monopoly.models.fields.deeds;

public class Street extends Deed{

	private int housePrice;
	private int hotelPrice;
	private String color;
	private int houseCount;
	private int currentRent;

	public Street(String name, int price, int mortgage, int housePrice, int hotelPrice, String color){
		super(name, price, mortgage);
		this.housePrice = housePrice;
		this.hotelPrice = hotelPrice;
		this.color = color;
		this.houseCount = 0;
		this.currentRent =  (int) (price * 0.1);
	}

	public int getHousePrice(){
		return housePrice;
	}

	public int getHotelPrice(){
		return hotelPrice;
	}

	public int getHouseCount() {
		return houseCount;
	}

	public int getCurrentRent(){
		return currentRent;
	}

	public void incrementHouseCount() {
		houseCount++;
		increaseRentRelativeToHouseCount();
	}

	public String getColor() {
		return color;
	}

	public void increaseRentRelativeToHouseCount() {
		switch (houseCount) {
			case 1: currentRent = currentRent * 5;
			case 2: currentRent = currentRent * 3;
			case 3: currentRent = (int)(currentRent * 2.5);
			case 4: currentRent = (int)(currentRent * 1.25);
		}
	}
}
