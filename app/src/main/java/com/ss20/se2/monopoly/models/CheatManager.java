package com.ss20.se2.monopoly.models;

import com.ss20.se2.monopoly.models.fields.deeds.Street;

public class CheatManager {

    Player latestCheater;
    Street cheatedStreet;

    public Street getCheatedStreet() {
        return cheatedStreet;
    }

    public void setCheatedStreet(Street cheatedStreet) {
        this.cheatedStreet = cheatedStreet;
    }

    public Player getLatestCheater() {
        return latestCheater;
    }

    public void setLatestCheater(Player latestCheater) {
        this.latestCheater = latestCheater;
    }

    public void flushCheater(){
        this.latestCheater = null;
    }





}
