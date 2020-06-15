package com.ss20.se2.monopoly;

import android.content.Context;

import com.ss20.se2.monopoly.models.Dice;
import com.ss20.se2.monopoly.models.GamePiece;
import com.ss20.se2.monopoly.models.Gameboard;
import com.ss20.se2.monopoly.models.fields.deeds.Railroad;
import com.ss20.se2.monopoly.models.fields.deeds.Street;
import com.ss20.se2.monopoly.models.fields.deeds.Utility;
import com.ss20.se2.monopoly.models.fields.deeds.UtilityType;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GameboardTest{

	@Test
	public void gameboardTest(){

		List<Street> streets = new ArrayList<>();
		List<Railroad> railroads = new ArrayList<>();
		List<Utility> utilities = new ArrayList<>();

		Utility u1 = new Utility("Wasserwerk", 200, 100, false, UtilityType.WATER_WORKS);
		Railroad r1 = new Railroad("Hauptbahnhof", 200, 100, false);
		Street s1 = new Street("Esterhazy", 100, 50, false,175, 300, "green");

		Context context = null;
		Gameboard gameboard = new Gameboard(context);
		gameboard.gameboardArray[0] = new GamePiece("Player 1");

		gameboard.getPosition("Player 1");
		assertEquals(0, gameboard.getPosition("Player 1"));
		gameboard.move("Player 1", 5);
		assertEquals(5, gameboard.getPosition("Player 1"));
		gameboard.move("Player 1", 20);
		assertEquals(25, gameboard.getPosition("Player 1"));
		gameboard.move("Player 1", 16);
		assertEquals(1, gameboard.getPosition("Player 1"));

		streets.add(s1);
		assertEquals(s1, gameboard.getStreets().get(0));
		railroads.add(r1);
		assertEquals(r1, gameboard.getRailroads().get(0));
		utilities.add(u1);
		assertEquals(u1, gameboard.getUtilities().get(0));
	}

	@Test
	public void diceTest(){

		Dice d1 = new Dice();
		int a = 1;
		int b = 6;
		d1.roll();
		assertTrue(d1.roll() >= a);
		assertTrue(d1.roll() <= b);

	}
}
