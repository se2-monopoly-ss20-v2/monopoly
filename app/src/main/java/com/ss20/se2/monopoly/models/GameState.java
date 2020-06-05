package com.ss20.se2.monopoly.models;

import android.content.Context;

import com.ss20.se2.monopoly.DeedManager;
import com.ss20.se2.monopoly.models.fields.GameTile;
import com.ss20.se2.monopoly.models.fields.deeds.Deed;
import com.ss20.se2.monopoly.models.fields.deeds.Street;
import com.ss20.se2.monopoly.network.gamestate.OnGameStateChangedListener;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class GameState implements Serializable{
	private static GameState instance;

	private Player currentActivePlayer;
	private int turnRotation;
	private Gameboard gameboard;
	private List<Deed> allDeeds;
	private DeedManager deedManager;
	private List<Player> players;
	private transient List<OnGameStateChangedListener> listeners;


	private GameState(){
		this.players = new ArrayList<>();
		this.listeners = new ArrayList<>();
		this.allDeeds = new ArrayList<>();
	}

	public static GameState getInstance(){
		if (instance == null){
			instance = new GameState();
		}
		return instance;
	}

	public void setupGame(List<LobbyPlayer> lobbyPlayers, Context context){
		for (LobbyPlayer lobbyPlayer : lobbyPlayers){
			Player player = new Player(lobbyPlayer.getName(), 100000,lobbyPlayer.getGamePiece(),1, lobbyPlayer.getAddress(),lobbyPlayer.getPort());
			this.players.add(player);
		}

		gameboard = new Gameboard(context);
		deedManager = new DeedManager(gameboard);

		for(GameTile gameTile : gameboard.gameTiles) {
			if (gameTile instanceof Deed) {
				allDeeds.add((Deed) gameTile);
			}
		}

		turnRotation = 0;

		currentActivePlayer = players.get(turnRotation);
	}

	public List<Player> getPlayers(){
		return players;
	}

	public Player getPlayerFrom(InetAddress address, int port) {
		for (Player p : players) {
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

	public Gameboard getGameboard(){
		return gameboard;
	}

	public void setGameboard(Gameboard gameboard){
		this.gameboard = gameboard;
	}

	public DeedManager getDeedManager(){
		return deedManager;
	}

	public List<Deed> getAllDeeds(){
		return allDeeds;
	}

	public void setAllDeeds(List<Deed> allDeeds){
		this.allDeeds = allDeeds;
	}

	public void updatePlayer(Player player) {
		for (Player p : players) {
			if (p.getAddress().equals(player.getAddress()) && p.getPort() == player.getPort()){
				p.setBalance(player.getBalance());
				p.setPlayersDeeds(player.getPlayersDeeds());
				p.setPlayersCards(player.getPlayersCards());

			}
		}
	}

	public void updateDeed(Deed deed){
		for (Deed d : allDeeds){
			if (deed.getName().equals(d.getName())) {
				d.setOwner(deed.getOwner());
			}
		}
	}
	public void playerEndedTurn() {

		if (turnRotation < (players.size() - 1)){
			turnRotation++;
		}else {
			turnRotation = 0;
		}

		currentActivePlayer = players.get(turnRotation);
	}

	public void setDeedManager(DeedManager deedManager){
		this.deedManager = deedManager;
	}

	public int getTurnRotation(){
		return turnRotation;
	}

	public void setTurnRotation(int turnRotation){
		this.turnRotation = turnRotation;
	}

	public void subscribe(OnGameStateChangedListener listener){
		listeners.add(listener);
	}

	public void unsubscribe(OnGameStateChangedListener listener){
		listeners.remove(listener);
	}

	public void notifyListenersForSetup(){
		for (OnGameStateChangedListener listener : listeners){
			listener.setupGameState(instance);
		}
	}

	public List<OnGameStateChangedListener> getListeners(){
		return listeners;
	}


	public void notifyListeners() {
		for (OnGameStateChangedListener listener: listeners) {
			listener.onGameStateChanged(instance);
		}
	}
}
