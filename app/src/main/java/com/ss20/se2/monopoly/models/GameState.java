package com.ss20.se2.monopoly.models;

import com.ss20.se2.monopoly.Utils;

import java.util.List;

public class GameState{
	private static GameState instance;
	private List<Player> players;
	private transient List<OnGameStateChangedListener> listeners;

	private GameState(){
	}

	public static GameState getInstance(){
		if (instance == null){
			instance = new GameState();
		}
		return instance;
	}

	public void setupGame(List<LobbyPlayer> lobbyPlayers){
		for (LobbyPlayer lobbyPlayer : lobbyPlayers){
			Player player = new Player(lobbyPlayer.getName(), 100000,lobbyPlayer.getGamePiece(),1, lobbyPlayer.getAddress(),lobbyPlayer.getPort());
			players.add(player);
		}
		players.get(0).setHasTurn(true);
	}

	public List<Player> getPlayers(){
		return players;
	}

	public void setPlayers(List<Player> players){
		this.players = players;
	}
	public void subscribe(OnGameStateChangedListener listener){
		listeners.add(listener);
	}

	public void unsubscribe(OnGameStateChangedListener listener){
		listeners.remove(listener);
	}

	public void notifyListeners(){
		for (OnGameStateChangedListener listener : listeners){
			listener.onGameStateChanged(instance);
		}
	}
}
