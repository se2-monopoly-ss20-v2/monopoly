package com.ss20.se2.monopoly.models.fields.deeds;

public class Street extends Deed{

	private int housePrice;
	private int hotelPrice;
	private String color;

	public Street(String name, int price, int mortgage, int housePrice, int hotelPrice, String color){
		super(name, price, mortgage);
		this.housePrice = housePrice;
		this.hotelPrice = hotelPrice;
		this.color = color;
	}

	public int getHousePrice(){
		return housePrice;
	}

	public int getHotelPrice(){
		return hotelPrice;
	}

	public String getColor() {
		return color;
	}
}
