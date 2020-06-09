package com.ss20.se2.monopoly;

import com.ss20.se2.monopoly.models.ChanceCardDeck;
import com.ss20.se2.monopoly.models.ChanceCardProcessor;
import com.ss20.se2.monopoly.models.GamePiece;
import com.ss20.se2.monopoly.models.Player;
import com.ss20.se2.monopoly.models.fields.cards.Card;
import com.ss20.se2.monopoly.models.fields.cards.ChanceCard;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.util.Stack;

public class ChanceCardTests {

    GamePiece cat;
    ChanceCardDeck deck;
    ChanceCardProcessor chanceCardProcessor;
    Player player;
    int oldBalance;

    @Before
    public void before(){
        cat = new GamePiece("Cat");
        deck = new ChanceCardDeck();
        chanceCardProcessor = new ChanceCardProcessor();
        player = new Player("MilkyChance", 0, cat, 0, InetAddress.getLoopbackAddress(), 10);
        oldBalance = player.getBalance();
    }

    @After
    public void after(){
        cat = null;
        deck = null;
        chanceCardProcessor = null;
        player = null;
        oldBalance = 0;
    }

    @Test
    public void jailCardTest(){

        ChanceCard jailCard = new ChanceCard("FREEJAIL", "Money can buy everything. You get out of jail. (Card needs to be kept until played)");
        chanceCardProcessor.performAction(player, jailCard);
        Assert.assertTrue(player.getPlayersCards().get(0)==jailCard);
    }

    @Test
    public void shoppingCardTest(){

        ChanceCard shopping = new ChanceCard("SHOPPING", "You go on a shopping trip. -$300");
        chanceCardProcessor.performAction(player, shopping);
        Assert.assertTrue(player.getBalance()==oldBalance-300);
    }

    @Test
    public void dentistTest(){

        ChanceCard dentist = new ChanceCard("DENTIST", "You need a convincing smile in your business. -$200 for your dentist");
        chanceCardProcessor.performAction(player, dentist);
        Assert.assertTrue(player.getBalance()==oldBalance-200);
    }

    @Test
    public void coffeeTest(){

        ChanceCard coffee = new ChanceCard("COFFEE", "NEED COFFEE. You stockpile on coffee. -$100");
        chanceCardProcessor.performAction(player,coffee);
        Assert.assertTrue(player.getBalance()==oldBalance-100);
    }

    @Test
    public void dayoffTest(){

        ChanceCard dayOff = new ChanceCard("DAYOFF", "During your day off, you lose some profit. -$50");
        chanceCardProcessor.performAction(player, dayOff);
        Assert.assertTrue(player.getBalance()==oldBalance-50);
    }

    @Test
    public void parkingTest(){

        ChanceCard parking = new ChanceCard("PARKING", "You left your card in a no-parking zone. -$25");
        chanceCardProcessor.performAction(player, parking);
        Assert.assertTrue(player.getBalance()==oldBalance-25);

    }

    @Test
    public void kickstartTest(){

        ChanceCard kickstart = new ChanceCard("KICKSTART", "Someone donated $100 to your kickstart project. Of course you are spending it on a new TV");
        chanceCardProcessor.performAction(player, kickstart);
        Assert.assertTrue(player.getBalance()==oldBalance+100);

    }
}
