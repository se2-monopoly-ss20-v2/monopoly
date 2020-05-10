package com.ss20.se2.monopoly.models;

import com.ss20.se2.monopoly.MainActivity;

public class Gameboard {

    public GamePiece[] gameboardArray;

    public Gameboard() {
        gameboardArray = new GamePiece[40];
    }

    public int getPosition(String name) {
        for (int i = 0; i < 40; i++) {
            if (gameboardArray[i] != null && name.equals(gameboardArray[i].getName())) {
                return i;
            }
        }
        return -1;
    }


    public void move(String name, int diceroll) {
        GamePiece temp = null;
        int position = 0;

        for (int i = 0; i < 40; i++) {
            if (gameboardArray[i] != null && name.equals(gameboardArray[i].getName())) {
                position = i;
                temp = gameboardArray[i];
                gameboardArray[i] = null;
            }
        }
        if (position + diceroll < 40) {
            if (gameboardArray[position + diceroll] == null) {
                gameboardArray[position + diceroll] = temp;
                temp = null;
            }
        } else {
            if (gameboardArray[(position + diceroll) - 40] == null) {
                gameboardArray[(position + diceroll) - 40] = temp;
                temp = null;
            }
        }
    }

    public void initializeUI(){

    }

    public int moveUI(String name, int i) {
        int position = getPosition(name);
        if (position + i >= 40) {
            return 1;
        }
        if (position + i < 10) {
            return 1;
        }
        if (position + i < 20) {
            return 2;
        }
        if (position + i < 30) {
            return 3;
        }
        if (position + i < 40) {
            return 4;
        }
        return -1;
    }

    public void newMoveUI(String name){
        int position = getPosition(name);


    }



}
