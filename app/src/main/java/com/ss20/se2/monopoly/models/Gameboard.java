package com.ss20.se2.monopoly.models;

import android.content.Context;
import android.util.Log;

import com.ss20.se2.monopoly.FieldDeserializer;
import com.ss20.se2.monopoly.Utils;
import com.ss20.se2.monopoly.models.fields.GameTile;
import com.ss20.se2.monopoly.models.fields.deeds.Street;

import java.util.ArrayList;

public class Gameboard {

	public GamePiece[] gameboardArray;
	public ArrayList<GameTile> gameTiles;
	private ArrayList<Street> streets;

	public Gameboard(Context context) {
		gameboardArray = new GamePiece[40];
		this.streets = new ArrayList<>();

		Utils utils = new Utils();
		String inputString = utils.getJSONFromAssets(context, "en");
		gameTiles = (ArrayList<GameTile>) utils.getGameTilesRelativeFrom(inputString);

		for (GameTile tile : gameTiles){
			if (tile instanceof Street) {
				streets.add((Street) tile);
			}
		}
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

	public ArrayList<Street> getStreets() {
		return streets;
	}

	public ArrayList<Street> getStreetsRelativeTo(String color) {
		ArrayList<Street> suitableStreets = new ArrayList<>();

		for (Street street : streets) {
			if (street.getColor().equals(color)) {
				suitableStreets.add(street);
			}
		}

		return suitableStreets;
	}
}