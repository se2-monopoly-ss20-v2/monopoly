package com.ss20.se2.monopoly;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.ss20.se2.monopoly.models.GamePiece;
import com.ss20.se2.monopoly.models.Gameboard;
import com.ss20.se2.monopoly.models.Player;
import com.ss20.se2.monopoly.models.fields.deeds.Street;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.InetAddress;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class GameboardDeedIntegratedTests{

	private Gameboard gameboard;

	@Before
	public void setup() {
		this.gameboard = new Gameboard(InstrumentationRegistry.getInstrumentation().getTargetContext());
	}

	@After
	public void cleanup() {
		this.gameboard = null;
	}

	@Test
	public void testStreetSize() {
		assertEquals(22, gameboard.getStreets().size());
	}

	@Test
	public void testRailRoadSize() {
		assertEquals(4, gameboard.getRailroads().size());
	}

	public void testUtilitySize() {
		assertEquals(2, gameboard.getUtilities().size());
	}

	public void testStreetColoring() {
		assertEquals(2, gameboard.getStreetsRelativeTo("violet").size());
		assertEquals(3, gameboard.getStreetsRelativeTo("lightBlue").size());
		assertEquals(3, gameboard.getStreetsRelativeTo("pink").size());
		assertEquals(3, gameboard.getStreetsRelativeTo("orange").size());
		assertEquals(3, gameboard.getStreetsRelativeTo("red").size());
		assertEquals(3, gameboard.getStreetsRelativeTo("yellow").size());
		assertEquals(3, gameboard.getStreetsRelativeTo("green").size());
		assertEquals(2, gameboard.getStreetsRelativeTo("darkBlue").size());
	}

}
