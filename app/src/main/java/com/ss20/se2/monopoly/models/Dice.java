package com.ss20.se2.monopoly.models;

public class Dice{

	public int roll(){
		int roll1 = (int) (6 * Math.random()) + 1;
		int roll2 = (int) (6 * Math.random()) + 1;

		int outcome = roll1 + roll2;

		return outcome;

		//TODO Implement Doubles Feature
		//if (roll1 == roll2)
	}
}
