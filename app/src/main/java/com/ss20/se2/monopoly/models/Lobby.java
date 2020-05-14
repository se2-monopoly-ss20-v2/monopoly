package com.ss20.se2.monopoly.models;

import com.ss20.se2.monopoly.network.NetworkUtilities;
import com.ss20.se2.monopoly.network.client.GameController;
import com.ss20.se2.monopoly.network.server.GameServer;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Lobby implements Serializable{

	private LobbyPlayer self;
	private List<LobbyPlayer> players;
	private boolean ready;
	private boolean started;
	private boolean active;
	private static Lobby instance;
	private transient List<OnLobbyDataChangedListener> listeners;

	private Lobby(){
		players = Collections.synchronizedList(new LinkedList<LobbyPlayer>());
		listeners = new LinkedList<>();
		ready = false;
		active = true;
	}

	public static Lobby getInstance(){
		if (instance == null){
			instance = new Lobby();
		}
		return instance;
	}

	public void subscribe(OnLobbyDataChangedListener listener){
		listeners.add(listener);
	}

	public void unsubscribe(OnLobbyDataChangedListener listener){
		listeners.remove(listener);
	}

	public void notifyListeners(){
		for (OnLobbyDataChangedListener listener : listeners){
			listener.onLobbyDataChanged(instance);
		}
	}

	public void addSelf(GameServer gameServer){
		LobbyPlayer lobbyPlayer = new LobbyPlayer("SERVER", gameServer.getAddress(), gameServer.getPort(), new GamePiece(""), true);
		self = lobbyPlayer;
		players.add(lobbyPlayer);
	}

	public void addSelf(GameController gameController){
		LobbyPlayer lobbyPlayer = new LobbyPlayer("CLIENT", gameController.getSocket().getLocalAddress(), gameController.getSocket().getLocalPort(), new GamePiece(""), false);
		self = lobbyPlayer;
		players.add(lobbyPlayer);
	}

	public LobbyPlayer getSelf(){
		return self;
	}

	public void setSelf(LobbyPlayer self){
		this.self = self;
	}

	public List<LobbyPlayer> getPlayers(){
		return players;
	}

	public boolean isReady(){
		return ready;
	}

	public void removePlayer(LobbyPlayer lobbyPlayer){
		players.remove(lobbyPlayer);
	}

	public void addPlayer(LobbyPlayer player){
		players.add(player);
	}

	public void setPlayers(List<LobbyPlayer> players){
		this.players = players;
	}

	public void setReady(boolean ready){
		this.ready = ready;
	}

	public boolean isActive(){
		return active;
	}

	public void closeLobby(){
		this.active = false;
		this.players = new LinkedList<>();
		this.ready = false;
		this.self = null;
		notifyListeners();
	}

	public void openLobby(){
		this.active = true;
	}

	public void changeGamePieceOfPlayer(LobbyPlayer lobbyPlayer, GamePiece gamePiece){
		for (LobbyPlayer player : players){
			if (player.equals(lobbyPlayer)){
				player.setGamePiece(gamePiece);
				break;
			}
		}
	}

	public void calculateReadyState(){
		boolean calcReady = true;
		if (players.size() >= 2 && players.size() <= NetworkUtilities.MAX_PLAYERS){
			for (LobbyPlayer player : players){
				if (!player.isHost() && !player.isReady()){
					calcReady = false;
					break;
				}
			}
			for (LobbyPlayer player : players){
				for (LobbyPlayer lobbyPlayer : players){
					if (!lobbyPlayer.equals(player) && player.getGamePiece().getName().equals(lobbyPlayer.getGamePiece().getName())){
						calcReady = false;
						break;
					}
				}
			}
		}else{
			calcReady = false;
		}
		this.ready = calcReady;
	}

	public boolean isStarted(){
		return started;
	}

	public void setStarted(boolean started){
		this.started = started;
	}
}
