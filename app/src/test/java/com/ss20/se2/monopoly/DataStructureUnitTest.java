package com.ss20.se2.monopoly;

import com.ss20.se2.monopoly.pojo.models.GamePiece;
import com.ss20.se2.monopoly.pojo.models.Player;
import com.ss20.se2.monopoly.pojo.models.deeds.ChanceCard;
import com.ss20.se2.monopoly.pojo.models.deeds.CommunityCard;
import com.ss20.se2.monopoly.pojo.models.deeds.Railroad;
import com.ss20.se2.monopoly.pojo.models.deeds.Street;
import com.ss20.se2.monopoly.pojo.models.deeds.Utility;

import org.junit.Test;

import static org.junit.Assert.*;

public class DataStructureUnitTest{

	@Test
	public void createGameTilesTest(){
		Railroad r1 = new Railroad("Hauptbahnhof", 200, 100);
		Railroad r2 = new Railroad("Südbahnhof", 200, 100);
		assertEquals(200, r1.getPrice());
		assertEquals(100, r1.getMortgage());
		assertEquals("Südbahnhof", r2.getName());
		Street s1 = new Street("Esterhazy", 100, 50, 175, 300);
		Street s2 = new Street("Operngasse", 300, 150, 275, 500);
		assertEquals(100, s1.getPrice());
		assertEquals(150, s2.getMortgage());
		assertEquals(175, s1.getHousePrice());
		assertEquals(500, s2.getHotelPrice());
		assertEquals("Esterhazy", s1.getName());
		assertEquals("Operngasse", s2.getName());
		ChanceCard cc1 = new ChanceCard("Go To Go", "Go to Go and earn XY.");
		ChanceCard cc2 = new ChanceCard("Go To Jail", "Go to Jail and do not earn XY.");
		assertEquals("Go To Go", cc1.getName());
		assertEquals("Go to Jail and do not earn XY.", cc2.getDescription());
		CommunityCard co1 = new CommunityCard("Go To", "no desc");
		CommunityCard co2 = new CommunityCard("Get Money", "desc");
		assertEquals("desc", co2.getDescription());
		assertEquals("Go To", co1.getName());
		Utility u1 = new Utility("Wasserwerk", 200, 10);
		Utility u2 = new Utility("Elektrizitätswerk", 200, 15);
		assertEquals("Wasserwerk", u1.getName());
		assertEquals(200, u2.getPrice());
		assertEquals(15, u2.getMortgage());
	}

	@Test
	public void createPlayer(){
		GamePiece gp = new GamePiece("shoe");
		assertEquals("shoe", gp.getName());
		Player p1 = new Player("Hannes", 5000, gp, 0);
		assertEquals("Hannes", p1.getName());
		assertEquals(5000, p1.getBalance());
		assertEquals(0, p1.getCurrentPosition());
		p1.setBalance(5000 - 250);
		assertEquals(4750, p1.getBalance());
		p1.setCurrentPosition(8);
		assertEquals(8, p1.getCurrentPosition());
		assertEquals(gp, p1.getSelectedPiece());
		Utility u1 = new Utility("Wasserwerk", 200, 100);
		Railroad r1 = new Railroad("Hauptbahnhof", 200, 100);
		Street s1 = new Street("Esterhazy", 100, 50, 175, 300);
		p1.addDeedToPlayer(u1);
		p1.addDeedToPlayer(r1);
		assertEquals(2, p1.getPlayersDeeds().size());
		p1.addDeedToPlayer(s1);
		assertEquals(3, p1.getPlayersDeeds().size());
		p1.removeDeedFromPlayer(r1);
		assertEquals(2, p1.getPlayersDeeds().size());
		ChanceCard cc1 = new ChanceCard("Go To Go", "Go to Go and earn XY.");
		CommunityCard co1 = new CommunityCard("Go To", "no desc");
		p1.addCardToPlayer(cc1);
		p1.addCardToPlayer(co1);
		assertEquals(2, p1.getPlayersCards().size());
		p1.removeCardFromPlayer(co1);
		assertEquals(1, p1.getPlayersCards().size());
	}
}
