package com.ss20.se2.monopoly.models;

import android.util.Log;

import com.ss20.se2.monopoly.Utils;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class GameState implements Serializable{
	private static GameState instance;
	private List<Player> players;
	private transient List<OnGameStateChangedListener> listeners;
	private Player currentActivePlayer;

	private GameState(){
		this.players = new ArrayList<>();
		this.listeners = new ArrayList<>();
	}

	public static GameState getInstance(){
		if (instance == null){
			instance = new GameState();
		}
		return instance;
	}

	public void setupGame(List<LobbyPlayer> lobbyPlayers){

		Log.d("GameState", lobbyPlayers.toString());

		for (LobbyPlayer lobbyPlayer : lobbyPlayers){
			Player player = new Player(lobbyPlayer.getName(), 100000,lobbyPlayer.getGamePiece(),1, lobbyPlayer.getAddress(),lobbyPlayer.getPort());
			this.players.add(player);
		}

		Log.d("GameState", players.toString());
		players.get(0).setHasTurn(true);
		currentActivePlayer = players.get(0);
	}

	public List<Player> getPlayers(){
		return players;
	}

	public Player getPlayerFrom(InetAddress address, int port) {
		Log.d("GameState", players.toString());
		for (Player p : players) {
			Log.d("GameState", address.toString());
			Log.d("GameState", p.getAddress().toString());
			Log.d("GameState", String.valueOf(port));
			Log.d("GameState", String.valueOf(p.getPort()));
			if (p.getAddress().equals(address) && p.getPort() == port) {
				return p;
			}
		}
		return null;
	}

	public void setPlayers(List<Player> players){
		this.players = players;
	}

	public Player getCurrentActivePlayer(){
		return currentActivePlayer;
	}

	public void setCurrentActivePlayer(Player currentActivePlayer){
		this.currentActivePlayer = currentActivePlayer;
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
