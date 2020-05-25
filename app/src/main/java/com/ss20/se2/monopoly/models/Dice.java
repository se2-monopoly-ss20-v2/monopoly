package com.ss20.se2.monopoly.models;

public class Dice{

	public int roll(){
		int diceroll = (int) (6 * Math.random()) + 1;

		return diceroll;
	}
}