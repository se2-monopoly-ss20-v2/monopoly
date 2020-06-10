package com.ss20.se2.monopoly;

import com.ss20.se2.monopoly.models.GamePiece;
import com.ss20.se2.monopoly.models.Lobby;
import com.ss20.se2.monopoly.models.LobbyPlayer;
import com.ss20.se2.monopoly.models.OnLobbyDataChangedListener;
import com.ss20.se2.monopoly.models.Player;
import com.ss20.se2.monopoly.network.server.GameServer;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LobbyUnitTest{

	@Test
	public void testLobbyInit(){
		Lobby lobby = Lobby.getInstance();
		assertEquals(false, lobby.isReady());
		assertEquals(true, lobby.isActive());
	}

	@Test
	public void testLobbyListeners(){
		Lobby lobby = Lobby.getInstance();
		OnLobbyDataChangedListener onLobbyDataChangedListener = new OnLobbyDataChangedListener(){
			@Override
			public void onLobbyDataChanged(Lobby lobby){
			}
		};
		lobby.subscribe(onLobbyDataChangedListener);
		assertEquals(true, lobby.getListeners().contains(onLobbyDataChangedListener));
		lobby.notifyListeners();
		lobby.unsubscribe(onLobbyDataChangedListener);
		assertEquals(false, lobby.getListeners().contains(onLobbyDataChangedListener));
	}

	@Test
	public void testLobbyServer(){
		Lobby lobby = Lobby.getInstance();
		GameServer gameServer = GameServer.getInstance();
		try{
			gameServer.setAddress(InetAddress.getLocalHost());
			gameServer.setPort(0);
		}catch (UnknownHostException e){
			assertTrue(false);
		}
		lobby.addSelf(gameServer);
		assertEquals(gameServer.getAddress(), lobby.getSelf().getAddress());
		assertEquals(gameServer.getPort(), lobby.getSelf().getPort());
		assertEquals(gameServer.getAddress(), lobby.getPlayers().get(0).getAddress());
		assertEquals(gameServer.getPort(), lobby.getPlayers().get(0).getPort());
	}

	@Test
	public void testLobbyClose(){
		Lobby lobby = Lobby.getInstance();
		lobby.closeLobby();
		assertEquals(false, lobby.isActive());
		assertEquals(0,lobby.getPlayers().size());
		assertEquals(false, lobby.isReady());
		assertEquals(null, lobby.getSelf());
	}

	@Test
	public void testLobbyCalculateReadyState(){
		Lobby lobby = Lobby.getInstance();
		List<LobbyPlayer> players = new LinkedList<>();
		players.add(new LobbyPlayer("Player1", null, 1, new GamePiece("Dino1"),true));
		players.add(new LobbyPlayer("Player1", null, 2, new GamePiece("Dino2"),false));
		players.add(new LobbyPlayer("Player1", null, 3, new GamePiece("Dino3"),false));
		players.add(new LobbyPlayer("Player1", null, 4, new GamePiece("Dino4"),false));
		players.add(new LobbyPlayer("Player1", null, 5, new GamePiece("Dino5"),false));
		players.add(new LobbyPlayer("Player1", null, 6, new GamePiece("Dino6"),false));
		players.add(new LobbyPlayer("Player1", null, 7, new GamePiece("Dino7"),false));
		players.add(new LobbyPlayer("Player1", null, 8, new GamePiece("Dino8"),false));
		players.add(new LobbyPlayer("Player1", null, 9, new GamePiece("Dino9"),false));
		lobby.setReady(true);
		lobby.calculateReadyState();
		assertEquals(false, lobby.isReady());
		lobby.setReady(true);
		lobby.setPlayers(players);
		for (LobbyPlayer player : lobby.getPlayers()){
			player.setReady(true);
		}
		lobby.calculateReadyState();
		assertEquals(false, lobby.isReady());
		lobby.removePlayer(lobby.getPlayers().get(4));
		lobby.calculateReadyState();
		assertEquals(true, lobby.isReady());
		lobby.getPlayers().get(2).setReady(false);
		lobby.calculateReadyState();
		assertEquals(false, lobby.isReady());
		lobby.setReady(true);
		lobby.getPlayers().get(2).setReady(true);
		lobby.getPlayers().get(2).setGamePiece(new GamePiece("Dino1"));
		lobby.calculateReadyState();
		assertEquals(false, lobby.isReady());
	}
}
