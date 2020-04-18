package com.ss20.se2.monopoly.pojo.models;

public class Player {

    private String name;
    private int balance;
    private GamePiece selectedPiece;
    //private ArrayList<Deed> deeds;
    //private ArrayList<> cards;
    private int currentPosition;

    public Player(String name, int balance, GamePiece selectedPiece, int currentPosition) {
        this.name = name;
        this.balance = balance;
        this.selectedPiece = selectedPiece;
        this.currentPosition = currentPosition;
    }


}
