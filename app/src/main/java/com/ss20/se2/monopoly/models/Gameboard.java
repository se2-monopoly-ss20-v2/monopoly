package com.ss20.se2.monopoly.models;

import android.content.Context;

import com.ss20.se2.monopoly.Utils;
import com.ss20.se2.monopoly.models.fields.GameTile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ss20.se2.monopoly.models.fields.deeds.Railroad;
import com.ss20.se2.monopoly.models.fields.deeds.Street;
import com.ss20.se2.monopoly.models.fields.deeds.Utility;

public class Gameboard implements Serializable{

	public GamePiece[] gameboardArray;
	public List<GameTile> gameTiles;
	private List<Street> streets;
	private List<Railroad> railroads;
	private List<Utility> utilities;

	public Gameboard(Context context) {
		gameboardArray = new GamePiece[40];
		this.streets = new ArrayList<>();
		this.railroads = new ArrayList<>();
		this.utilities = new ArrayList<>();
		Utils utils = new Utils();
		String inputString = utils.getJSONFromAssets(context, "en");
		gameTiles = utils.getGameTilesRelativeFrom(inputString);

		for (GameTile tile : gameTiles){
			if (tile instanceof Street) {
				streets.add((Street) tile);
			} else if (tile instanceof Railroad) {
				railroads.add((Railroad) tile);
			} else if (tile instanceof Utility) {
				utilities.add((Utility) tile);
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
			}
		} else {
			if (gameboardArray[(position + diceroll) - 40] == null) {
				gameboardArray[(position + diceroll) - 40] = temp;
			}
		}
	}

	public List<Railroad> getRailroads(){
		return railroads;
	}

	public List<Street> getStreets() {
		return streets;
	}

	public void setStreets(List<Street> streets){
		this.streets = streets;
	}

	public List<Utility> getUtilities(){
		return utilities;
	}

	public List<GameTile> getGameTiles(){
		return gameTiles;
	}

	public void setGameTiles(List<GameTile> gameTiles){
		this.gameTiles = gameTiles;
	}

	public List<Street> getStreetsRelativeTo(String color) {
		ArrayList<Street> suitableStreets = new ArrayList<>();

		for (Street street : streets) {
			if (street.getColor().equals(color)) {
				suitableStreets.add(street);
			}
		}

		return suitableStreets;
	}
}