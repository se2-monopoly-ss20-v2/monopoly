package com.ss20.se2.monopoly.models;

import com.ss20.se2.monopoly.models.fields.deeds.Street;

import java.io.Serializable;

public class CheatManager implements Serializable {

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

    /**
     * resets latest cheater
     */
    public void flushCheater(){
        this.latestCheater = null;
    }





}
