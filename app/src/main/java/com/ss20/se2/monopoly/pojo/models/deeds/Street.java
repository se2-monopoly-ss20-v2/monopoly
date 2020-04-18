package com.ss20.se2.monopoly.pojo.models.deeds;

import com.ss20.se2.monopoly.pojo.models.deeds.Deed;

public class Street extends Deed {

    private int housePrice;
    private int hotelPrice;

    public Street(String name, int balance, int mortgage, int housePrice, int hotelPrice) {
        super(name, balance, mortgage);

        this.housePrice = housePrice;
        this.hotelPrice = hotelPrice;
    }

    public int getHousePrice() {
        return housePrice;
    }

    public void setHousePrice(int housePrice) {
        this.housePrice = housePrice;
    }

    public int getHotelPrice() {
        return hotelPrice;
    }

    public void setHotelPrice(int hotelPrice) {
        this.hotelPrice = hotelPrice;
    }
}
