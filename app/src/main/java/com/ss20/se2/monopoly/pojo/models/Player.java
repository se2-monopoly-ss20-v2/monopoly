package com.ss20.se2.monopoly.pojo.models;

public class Player {

    private String name;
    private int balance;
    private GamePiece selectedPiece;
    private int currentPosition;

    public Player(String name, int balance, GamePiece selectedPiece, int currentPosition) {
        this.name = name;
        this.balance = balance;
        this.selectedPiece = selectedPiece;
        this.currentPosition = currentPosition;
    }


    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public GamePiece getSelectedPiece() {
        return selectedPiece;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }
}
