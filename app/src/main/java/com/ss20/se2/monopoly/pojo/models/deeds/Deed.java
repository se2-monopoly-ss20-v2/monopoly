package com.ss20.se2.monopoly.pojo.models.deeds;

public abstract class Deed {
    private String name;
    private int price;
    private int mortgage;

    public Deed(String name, int price, int mortgage) {
        this.name = name;
        this.price = price;
        this.mortgage = mortgage;
    }

    public Deed(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return price;
    }

    public int getMortgage() {
        return mortgage;
    }
}
