package com.ss20.se2.monopoly.models.fields.deeds;

public class Street extends Deed{

	private int housePrice;
	private int hotelPrice;
	private String color;
	private int houseCount;
	private int currentRent;
	private int initialRent;
	private boolean hasHotel = false;

	public Street(String name, int price, int mortgage, boolean isMortgaged, int housePrice, int hotelPrice, String color){
		super(name, price, mortgage, isMortgaged);
		this.housePrice = housePrice;
		this.hotelPrice = hotelPrice;
		this.color = color;
		this.houseCount = 0;

		double rent = price * 0.1;
		this.initialRent = (int) rent;
		this.currentRent =  this.initialRent;
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

	public int getInitialRent() { return initialRent; }

	public void incrementHouseCount() {
		houseCount++;
		increaseRentRelativeToHouseCount();
	}

	public boolean getHasHotel(){
		return this.hasHotel;
	}

	public void upgradeToHotel(){
		this.hasHotel = true;
		houseCount = 0;
	}

	public void setHouseCount(int houseCount){
		this.houseCount = houseCount;
	}

	public void setCurrentRent(int currentRent){
		this.currentRent = currentRent;
	}

	public void setInitialRent(int initialRent){
		this.initialRent = initialRent;
	}

	public void setHasHotel(boolean hasHotel){
		this.hasHotel = hasHotel;
	}

	public String getColor() {
		return color;
	}

	private void increaseRentRelativeToHouseCount() {
		switch (houseCount) {
			case 1:
				currentRent = currentRent * 5;
				break;
			case 2:
				currentRent = currentRent * 3;
				break;
			case 3:
				currentRent = (int)(currentRent * 2.5);
				break;
			case 4:
				currentRent = (int)(currentRent * 1.25);
				break;
			default:
				break;
		}
	}
}
