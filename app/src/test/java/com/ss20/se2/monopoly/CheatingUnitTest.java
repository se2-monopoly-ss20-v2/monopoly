package com.ss20.se2.monopoly;


import com.ss20.se2.monopoly.models.*;
import com.ss20.se2.monopoly.models.fields.deeds.Street;
import org.junit.Test;


import java.net.InetAddress;

import static org.junit.Assert.*;

public class CheatingUnitTest {

    @Test
    public void playerCheatsStreetTest(){

        Gameboard gb = GameState.getInstance().getGameboard();

        GamePiece dog = new GamePiece("Dog");
        DeedManager deedManager = new DeedManager(gb);
        Street street = new Street("Cheat Avenue", 123,10,false,10,100,"Yellow");

        Player cheater = new Player("Cheater", 5000, dog, 0, InetAddress.getLoopbackAddress(), 10);

        deedManager.cheatStreet(street, cheater);

        assertTrue((cheater.getPlayersDeeds().get(0) == street) && (cheater.getBalance() == 5000));

    }

    @Test
    public void latestCheaterTest(){

        Gameboard gb = GameState.getInstance().getGameboard();

        GamePiece dog = new GamePiece("Dog");
        CheatManager cheatManager = new CheatManager();
        DeedManager deedManager = new DeedManager(gb);
        Street street = new Street("Cheat Avenue", 123,10,false,10,100,"Yellow");

        Player cheater = new Player("Cheater", 5000, dog, 0, InetAddress.getLoopbackAddress(), 10);
        Player honestPlayer = new Player("Honesto", 300, dog, 0, InetAddress.getLoopbackAddress(), 10);

        deedManager.cheatStreet(street, cheater);
        cheatManager.setLatestCheater(cheater);

        assertNotEquals(cheatManager.getLatestCheater(), honestPlayer);
        assertSame(cheatManager.getLatestCheater(), cheater);

    }

    @Test
    public void latestCheatedStreetTest(){
        Gameboard gb = GameState.getInstance().getGameboard();

        GamePiece dog = new GamePiece("Dog");
        CheatManager cheatManager = new CheatManager();
        DeedManager deedManager = new DeedManager(gb);
        Street street = new Street("Cheat Avenue", 123,10,false,10,100,"Yellow");

        Player cheater = new Player("Cheater", 5000, dog, 0, InetAddress.getLoopbackAddress(), 10);
        Player honestPlayer = new Player("Honesto", 300, dog, 0, InetAddress.getLoopbackAddress(), 10);

        deedManager.cheatStreet(street, cheater);
        cheatManager.setCheatedStreet(street);

        assertSame(cheatManager.getCheatedStreet(),street);

    }

    @Test
    public void flushCheaterTest(){
        Gameboard gb = GameState.getInstance().getGameboard();

        GamePiece dog = new GamePiece("Dog");
        CheatManager cheatManager = new CheatManager();
        DeedManager deedManager = new DeedManager(gb);
        Street street = new Street("Cheat Avenue", 123,10,false,10,100,"Yellow");

        Player cheater = new Player("Cheater", 5000, dog, 0, InetAddress.getLoopbackAddress(), 10);
        Player honestPlayer = new Player("Honesto", 300, dog, 0, InetAddress.getLoopbackAddress(), 10);

        deedManager.cheatStreet(street, cheater);
        cheatManager.setCheatedStreet(street);

        //assertSame(cheatManager.getLatestCheater(), cheater);
        cheatManager.flushCheater();

        assertNull(cheatManager.getLatestCheater());

    }


}
