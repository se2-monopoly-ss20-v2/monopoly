package com.ss20.se2.monopoly;

import com.ss20.se2.monopoly.models.*;
import com.ss20.se2.monopoly.models.fields.cards.ChanceCard;
import com.ss20.se2.monopoly.models.fields.cards.CommunityCard;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;

public class CommunityCardTest {

    GamePiece cat;
    CommunityCardDeck deck;
    CommunityCardProcessor communityCardProcessor;
    Player player;
    int oldBalance;

    @Before
    public void before(){
        cat = new GamePiece("Cat");
        deck = new CommunityCardDeck();
        communityCardProcessor = new CommunityCardProcessor();
        player = new Player("Communitatore", 0, cat, 0, InetAddress.getLoopbackAddress(), 10);
        oldBalance = player.getBalance();
    }

    @After
    public void after(){
        cat = null;
        deck = null;
        communityCardProcessor = null;
        player = null;
        oldBalance = 0;
    }

    @Test
    public void birthdayCardTest(){

        CommunityCard birthdayCard = new CommunityCard("BIRTHDAY", "It's your birthday, your parents give you $50");
        communityCardProcessor.performAction(player, birthdayCard);
        Assert.assertTrue(player.getBalance()==oldBalance+50);
    }

    @Test
    public void luckTest(){

        ChanceCard luck = new ChanceCard("LUCK", "Today is your lucky-day, you find $200 on the street");
        communityCardProcessor.performAction(player, luck);
        Assert.assertTrue(player.getBalance()==oldBalance+200);
    }

    @Test
    public void badLuckTest(){

        ChanceCard badLuck = new ChanceCard("BADLUCK","You lost your wallet, $150 gone...");
        communityCardProcessor.performAction(player, badLuck);
        Assert.assertTrue(player.getBalance()==oldBalance-150);
    }

    @Test
    public void dateTest(){

        ChanceCard date = new ChanceCard("DATE", "To impress your date you pay the bill. Thankfully you were eating fast-food. -15$");
        communityCardProcessor.performAction(player,date);
        Assert.assertTrue(player.getBalance()==oldBalance-15);
    }

    @Test
    public void stockTest(){

        ChanceCard stock = new ChanceCard("STOCK", "You invested in facemasks. $300 for you");
        communityCardProcessor.performAction(player, stock);
        Assert.assertTrue(player.getBalance()==oldBalance+300);
    }

    @Test
    public void parkingTest(){

        ChanceCard hospital = new ChanceCard("HOSPITAL", "You fell of your chair while complaining about your employees. The hospital bill is $200");
        communityCardProcessor.performAction(player, hospital);
        Assert.assertTrue(player.getBalance()==oldBalance-200);
    }

}
