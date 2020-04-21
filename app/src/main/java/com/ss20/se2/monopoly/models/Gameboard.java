package com.ss20.se2.monopoly.models;

public class Gameboard{

	public GamePiece[] gameboardArray;

	public Gameboard(){
		gameboardArray = new GamePiece[40];
	}


	public void move(String name, int diceroll){
		GamePiece temp = null;
		int position = 0;
		int ret = 0;
		
		for (int i = 0; i < 40; i++){
			if (gameboardArray[i] != null && name.equals(gameboardArray[i].getName())){
				position = i;
				temp = gameboardArray[i];
				gameboardArray[i] = null;
			}
		}
		if (position + diceroll < 40){
			if (gameboardArray[position + diceroll] == null){
				gameboardArray[position + diceroll] = temp;
				temp = null;
			}
		} else {
			if (gameboardArray[(position + diceroll) - 40] == null){
				gameboardArray[(position + diceroll) - 40] = temp;
				temp = null;
			}
		}
		for (int j = 1; j <= diceroll; j++){
			if (position + j <= 10){
				ret = 1;
			}
			if (position + j <= 20 && position + j > 10){
				ret = 2;
			}
			if (position + j <= 30 && position + j > 20){
				ret = 3;
			}
			if (position + j <= 40 && position + j > 30){
				ret = 4;
			}
		}
	}
}
