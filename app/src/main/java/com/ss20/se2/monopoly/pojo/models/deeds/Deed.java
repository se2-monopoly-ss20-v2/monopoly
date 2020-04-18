package com.ss20.se2.monopoly.pojo.models.deeds;

public abstract class Deed extends GameTile {
    private int price;
    private int mortgage;

    Deed(String name, int price, int mortgage) {
        super(name);

        this.price = price;
        this.mortgage = mortgage;
    }

    public int getPrice() {
        return price;
    }

    public int getMortgage() {
        return mortgage;
    }
}
