package com.ss20.se2.monopoly;

import com.ss20.se2.monopoly.models.GamePiece;
import com.ss20.se2.monopoly.models.Player;
import com.ss20.se2.monopoly.models.fields.cards.ChanceCard;
import com.ss20.se2.monopoly.models.fields.cards.CommunityCard;
import com.ss20.se2.monopoly.models.fields.deeds.Railroad;
import com.ss20.se2.monopoly.models.fields.deeds.Street;
import com.ss20.se2.monopoly.models.fields.deeds.Utility;
import com.ss20.se2.monopoly.models.fields.specials.Special;
import com.ss20.se2.monopoly.models.fields.specials.SpecialFieldType;

import org.junit.Test;

import java.net.InetAddress;

import static org.junit.Assert.*;

public class DataStructureUnitTest{

	@Test
	public void createGameTilesTest(){
		Railroad r1 = new Railroad("Hauptbahnhof", 200, 100, false);
		Railroad r2 = new Railroad("Südbahnhof", 200, 100, false);
		assertEquals(200, r1.getPrice());
		assertEquals(100, r1.getMortgage());
		assertEquals("Südbahnhof", r2.getName());
		Street s1 = new Street("Esterhazy", 100, 50, false, 175, 300, "pink");
		Street s2 = new Street("Operngasse", 300, 150, false,275, 500, "violet");
		assertEquals(100, s1.getPrice());
		assertEquals(150, s2.getMortgage());
		assertEquals(175, s1.getHousePrice());
		assertEquals(500, s2.getHotelPrice());
		assertEquals("violet", s2.getColor());
		assertEquals("pink", s1.getColor());
		assertEquals("Esterhazy", s1.getName());
		assertEquals("Operngasse", s2.getName());
		ChanceCard cc1 = new ChanceCard("Go To Go","Go To Go");
		ChanceCard cc2 = new ChanceCard("Go To Jail","Go To Jail");
		assertEquals("Go To Go", cc1.getName());
		CommunityCard co1 = new CommunityCard("Go To","Go To");
		CommunityCard co2 = new CommunityCard("Get Money","Get Money");
		assertEquals("Go To", co1.getName());
		Utility u1 = new Utility("Wasserwerk", 200, 10, false);
		Utility u2 = new Utility("Elektrizitätswerk", 200, 15, false);
		assertEquals("Wasserwerk", u1.getName());
		assertEquals(200, u2.getPrice());
		assertEquals(15, u2.getMortgage());
		Special special = new Special("Free Parking", SpecialFieldType.FREEPARKING);
		assertEquals(SpecialFieldType.FREEPARKING, special.getFieldType());
	}

	@Test
	public void createPlayer(){
		GamePiece gp = new GamePiece("shoe");
		assertEquals("shoe", gp.getName());
		Player p1 = new Player("Hannes", 5000, gp, 0, InetAddress.getLoopbackAddress(), 10);
		assertEquals("Hannes", p1.getName());
		assertEquals(5000, p1.getBalance());
		assertEquals(0, p1.getCurrentPosition());
		p1.setBalance(5000 - 250);
		assertEquals(4750, p1.getBalance());
		p1.setCurrentPosition(8);
		assertEquals(8, p1.getCurrentPosition());
		assertEquals(gp, p1.getSelectedPiece());
		Utility u1 = new Utility("Wasserwerk", 200, 100, false);
		Railroad r1 = new Railroad("Hauptbahnhof", 200, 100, false);
		Street s1 = new Street("Esterhazy", 100, 50, false, 175, 300, "green");
		p1.addDeedToPlayer(u1);
		p1.addDeedToPlayer(r1);
		assertEquals(2, p1.getPlayersDeeds().size());
		p1.addDeedToPlayer(s1);
		assertEquals(3, p1.getPlayersDeeds().size());
		p1.removeDeedFromPlayer(r1);
		assertEquals(2, p1.getPlayersDeeds().size());
		ChanceCard cc1 = new ChanceCard("Go To Go","Go To Go");
		CommunityCard co1 = new CommunityCard("Go To","Go To");
		p1.addCardToPlayer(cc1);
		p1.addCardToPlayer(co1);
		assertEquals(2, p1.getPlayersCards().size());
		p1.removeCardFromPlayer(co1);
		assertEquals(1, p1.getPlayersCards().size());
		s1.setOwner(p1);
		assertEquals(p1, s1.getOwner());
	}
}
