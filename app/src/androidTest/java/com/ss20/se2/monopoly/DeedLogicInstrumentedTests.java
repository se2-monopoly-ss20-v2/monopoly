package com.ss20.se2.monopoly;

import android.util.Log;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.ss20.se2.monopoly.models.Dice;
import com.ss20.se2.monopoly.models.GamePiece;
import com.ss20.se2.monopoly.models.Gameboard;
import com.ss20.se2.monopoly.models.Player;
import com.ss20.se2.monopoly.models.fields.deeds.Street;
import com.ss20.se2.monopoly.view.GameboardActivity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.InetAddress;

@RunWith(AndroidJUnit4.class)
public class DeedLogicInstrumentedTests{

	Gameboard gameboard;
	DeedManager deedManager;
	Player player1;
	Player player2;
	Street street1;
	Street street2;
	Dice dice;

	@Before
	public void setup() {
		this.gameboard = new Gameboard(InstrumentationRegistry.getInstrumentation().getTargetContext());
		this.deedManager = new DeedManager(gameboard);
		this.player1 = new Player("Wunderwutzi", 1000, new GamePiece("shoe"), 0, InetAddress.getLoopbackAddress(), 1);
		this.player1 = new Player("Hannes", 1000, new GamePiece("dog"), 0, InetAddress.getLoopbackAddress(), 2);
		this.street1 = (Street) gameboard.gameTiles.get(1);
		this.street2 = (Street) gameboard.gameTiles.get(3);
	}

	@After
	public void cleanup() {
		this.gameboard = null;
		this.deedManager = null;
		this.player1 = null;
		this.player2 = null;
		this.dice = null;
	}

	@Test
	public void testAcquiringDeed() {
		int amount = 3;
		Street street = (Street)gameboard.gameTiles.get(amount);
		int expectedBalance = player1.getBalance() - street.getPrice();

		assertEquals( expectedBalance, deedManager.performAcquiringDeed(street, player1));
		assertEquals(1, player1.getPlayersDeeds().size());
		assertEquals(street, player1.getPlayersDeeds().get(0));
	}

	@Test
	public void checkIfPlayerOwnsAllDeedsOfSameColor() {
		int amount = 1;

		int balanceAfterFirstPurchase = player1.getBalance() - street1.getPrice();
		int balanceAfterSecond = balanceAfterFirstPurchase - street2.getPrice();

		assertEquals(balanceAfterFirstPurchase, deedManager.performAcquiringDeed(street1, player1));
		assertFalse(deedManager.playerOwnsAllStreetsOf(street1.getColor(), player1));
		assertEquals(balanceAfterSecond, deedManager.performAcquiringDeed(street2, player1));
		assertEquals(2, player1.getPlayersDeeds().size());
		assertTrue(deedManager.playerOwnsAllStreetsOf(street1.getColor(), player1));
	}

	@Test
	public void performAcquiringHouseAndHotelOnDeed() {
		int balanceAfterFirstPurchase = player1.getBalance() - street1.getPrice();
		int balanceAfterSecond = balanceAfterFirstPurchase - street2.getPrice();

		assertEquals(deedManager.performAcquiringDeed(street1, player1), balanceAfterFirstPurchase);
		assertEquals(deedManager.performAcquiringDeed(street2, player1), balanceAfterSecond);
		assertTrue(deedManager.playerOwnsAllStreetsOf(street1.getColor(), player1));

		deedManager.performAcquiringHouseFor(street1, player1);
		assertEquals(1, street1.getHouseCount());
		deedManager.performAcquiringHouseFor(street1, player1);
		assertEquals(2, street1.getHouseCount());
		deedManager.performAcquiringHouseFor(street1, player1);
		assertEquals(3, street1.getHouseCount());
		deedManager.performAcquiringHouseFor(street1, player1);
		assertEquals(4, street1.getHouseCount());

		assertFalse(street1.getHasHotel());
		deedManager.performAcquiringHotelFor(street1, player1);
		assertTrue(street1.getHasHotel());
	}
}
