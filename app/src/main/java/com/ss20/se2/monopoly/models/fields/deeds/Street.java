package com.ss20.se2.monopoly.models.fields.deeds;

public class Street extends Deed{

	private int housePrice;
	private int hotelPrice;

	public Street(String name, int price, int mortgage, int housePrice, int hotelPrice){
		super(name, price, mortgage);
		this.housePrice = housePrice;
		this.hotelPrice = hotelPrice;
	}

	public int getHousePrice(){
		return housePrice;
	}

	public int getHotelPrice(){
		return hotelPrice;
	}
}
