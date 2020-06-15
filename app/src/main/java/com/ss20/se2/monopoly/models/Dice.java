package com.ss20.se2.monopoly.models;

import java.util.Random;

public class Dice{

	public int roll(){
		Random r = new Random();
		int diceroll = r.nextInt(5) + 1;
		return diceroll;
	}
}