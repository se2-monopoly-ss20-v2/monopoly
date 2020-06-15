package com.ss20.se2.monopoly;

import android.content.Context;

import com.google.android.play.core.splitcompat.SplitCompatApplication;
import com.ss20.se2.monopoly.models.GamePiece;
import com.ss20.se2.monopoly.models.GameState;
import com.ss20.se2.monopoly.models.Gameboard;
import com.ss20.se2.monopoly.models.LobbyPlayer;
import com.ss20.se2.monopoly.models.Player;
import com.ss20.se2.monopoly.models.fields.deeds.Deed;
import com.ss20.se2.monopoly.models.fields.deeds.Railroad;
import com.ss20.se2.monopoly.models.fields.deeds.Street;
import com.ss20.se2.monopoly.models.fields.deeds.Utility;
import com.ss20.se2.monopoly.models.fields.deeds.UtilityType;
import com.ss20.se2.monopoly.network.gamestate.OnGameStateChangedListener;

import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.booleanThat;
import static org.mockito.Mockito.mock;

public class GameStateUnitTest{

	@Before
	public void setup() {
		GameState.getInstance().setAllDeeds(new ArrayList<Deed>());
		GameState.getInstance().setPlayers(new ArrayList<Player>());
	}

	@Test (expected = NullPointerException.class)
	public void setupGameState() throws UnknownHostException{
		Context context = mock(Context.class);
		ArrayList<LobbyPlayer> players = new ArrayList<>();

		LobbyPlayer p1 = new LobbyPlayer("hannes", InetAddress.getByName("192.168.0.1"), 10, new GamePiece("shoe"), false);
		LobbyPlayer p2 = new LobbyPlayer("hannes2", InetAddress.getByName("192.168.0.2"), 11, new GamePiece("hat"), false);

		players.add(p1);
		players.add(p2);

		GameState.getInstance().setupGame(players, context);
	}

	@Test
	public void getAndSetGameStateAttributes() throws UnknownHostException{
		List<Player> players = GameState.getInstance().getPlayers();
		assertEquals(0, players.size());

		Player active = new Player("Hannes", 20, new GamePiece("shoe"), 0,InetAddress.getByName("192.168.0.1"), 10 );
		GameState.getInstance().setCurrentActivePlayer(active);
		Gameboard gb = GameState.getInstance().getGameboard();
		GameState.getInstance().setGameboard(gb);
		List<Deed> deeds = GameState.getInstance().getAllDeeds();
		Street firstStreet = new Street("ASDF", 200, 20, false, 20, 10, "orange");
		Street secondStreet = new Street("JKL", 200, 20, false,20, 10, "orange");
		deeds.add(firstStreet);


		deeds.add(secondStreet);
		GameState.getInstance().setAllDeeds(deeds);
		secondStreet.setOwner(active);
		GameState.getInstance().updateDeed(secondStreet);

		ArrayList<Player> playersNew = new ArrayList<>();
		Player second = new Player("Hannes2", 20, new GamePiece("shoe"), 0,InetAddress.getByName("192.168.0.2"), 11 );
		playersNew.add(active);
		playersNew.add(second);
		GameState.getInstance().setPlayers(playersNew);
		GameState.getInstance().playerEndedTurn();
		GameState.getInstance().playerEndedTurn();
		GameState.getInstance().updatePlayer(second);

		int balance = GameState.getInstance().getBalanceOfSpecificPlayer(second);
		Player wrongPlayer = new Player("Hannes23", 20, new GamePiece("shoe"), 0,InetAddress.getByName("192.168.0.3"), 103 );
		int negative = GameState.getInstance().getBalanceOfSpecificPlayer(wrongPlayer);

		int rotation = GameState.getInstance().getTurnRotation();
		GameState.getInstance().setTurnRotation(rotation);

		assertEquals(-1, negative);
		assertEquals(second.getBalance(), balance);
		assertEquals(2, GameState.getInstance().getAllDeeds().size());
		assertEquals(active, GameState.getInstance().getCurrentActivePlayer());
	}

	@Test
	public void listenerTests() {
		final boolean[] setupDone = {false};
		final boolean[] changeDone = {false};

		OnGameStateChangedListener listener = new OnGameStateChangedListener(){
			@Override
			public void onGameStateChanged(GameState gameState){
				changeDone[0] = true;

			}

			@Override
			public void setupGameState(GameState gameState){
				setupDone[0] = true;
			}
		};

		GameState.getInstance().subscribe(listener);
		GameState.getInstance().notifyListenersForSetup();
		GameState.getInstance().notifyListeners();
		assertEquals(1, GameState.getInstance().getListeners().size());
		assertTrue(setupDone[0]);
		assertTrue(changeDone[0]);
	}

	@Test
	public void GameStateUtilityCheck() throws UnknownHostException {
		Street street = new Street("ASDF", 200, 20, false, 20, 10, "orange");
		Player player = new Player("Hannes2", 20, new GamePiece("shoe"), 0,InetAddress.getByName("192.168.0.2"), 11 );
		Utility water = new Utility("WaterWorks", 140, 75, false, UtilityType.WATER_WORKS);
		Utility electric = new Utility("Electric", 140, 75, false, UtilityType.ELECTRIC_COMPANY);

		List<Deed> allDeeds = new ArrayList<>();
		allDeeds.add(street);
		allDeeds.add(water);
		allDeeds.add(electric);
		GameState.getInstance().setAllDeeds(allDeeds);
		water.setOwner(player);

		GameState.getInstance().updateDeed(water);

		assertFalse(GameState.getInstance().playerOwnsBothUtilities(player));
		electric.setOwner(player);
		GameState.getInstance().updateDeed(electric);
		assertTrue(GameState.getInstance().playerOwnsBothUtilities(player));

	}

	@Test
	public void GameStateRailroadCount() throws UnknownHostException {
		Street street = new Street("ASDF", 200, 20,false, 20, 10, "orange");
		Player player = new Player("Hannes2", 20, new GamePiece("shoe"), 0,InetAddress.getByName("192.168.0.2"), 11 );
		Railroad railroad = new Railroad("Haupt", 200, 100, false);
		Railroad railroad2 = new Railroad("Neben", 200, 100, false);

		List<Deed> allDeeds = new ArrayList<>();
		allDeeds.add(street);
		allDeeds.add(railroad);
		allDeeds.add(railroad2);
		GameState.getInstance().setAllDeeds(allDeeds);

		assertEquals(0,GameState.getInstance().countOfPlayersRailroads(player));

		railroad.setOwner(player);
		GameState.getInstance().updateDeed(railroad);
		assertEquals(1, GameState.getInstance().countOfPlayersRailroads(player));

		railroad2.setOwner(player);
		GameState.getInstance().updateDeed(railroad2);

		assertEquals(2, GameState.getInstance().countOfPlayersRailroads(player));

	}
}
