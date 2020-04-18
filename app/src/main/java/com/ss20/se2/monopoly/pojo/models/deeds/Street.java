package com.ss20.se2.monopoly.pojo.models.deeds;

import com.ss20.se2.monopoly.pojo.models.deeds.Deed;

public class Street extends Deed {

    private int housePrice;
    private int hotelPrice;

    public Street(String name, int price, int mortgage, int housePrice, int hotelPrice) {
        super(name, price, mortgage);

        this.housePrice = housePrice;
        this.hotelPrice = hotelPrice;
    }

    public int getHousePrice() {
        return housePrice;
    }

    public int getHotelPrice() {
        return hotelPrice;
    }
    
}
