package com.ss20.se2.monopoly.network.server;

import android.util.Log;

import com.ss20.se2.monopoly.models.GamePiece;
import com.ss20.se2.monopoly.models.Lobby;
import com.ss20.se2.monopoly.models.LobbyPlayer;
import com.ss20.se2.monopoly.models.Player;
import com.ss20.se2.monopoly.models.fields.deeds.Deed;
import com.ss20.se2.monopoly.network.NetworkUtilities;
import com.ss20.se2.monopoly.network.client.ChangeGamePieceNetworkMessage;
import com.ss20.se2.monopoly.network.client.JoinLobbyNetworkMessage;
import com.ss20.se2.monopoly.network.client.LeaveLobbyNetworkMessage;
import com.ss20.se2.monopoly.network.client.NetworkMessage;
import com.ss20.se2.monopoly.network.client.ReadyLobbyNetworkMessage;
import com.ss20.se2.monopoly.network.shared.GameActions;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

class RequestHandler implements Runnable{

	private final AtomicBoolean running = new AtomicBoolean(false);
	private static RequestHandler instance;
	private GameActionProcessor gameActionProcessor;
	private BlockingQueue<NetworkMessage> queue;

	private RequestHandler(){
		this.queue = new LinkedBlockingDeque<NetworkMessage>();
		this.gameActionProcessor = new GameActionProcessor();
		start();
	}

	/**
	 * Returns a singleton instance of this class
	 *
	 * @return
	 */
	static RequestHandler getInstance(){
		if (instance == null){
			instance = new RequestHandler();
		}
		return instance;
	}

	void start(){
		running.set(true);
		new Thread(this).start();
	}

	void stop(){
		running.set(false);
	}

	/**
	 * Consumer thread that fetches requests from the thread-safe queue and then processes them.
	 */
	@Override
	public void run(){
		while (running.get()){
			try{
				NetworkMessage request = queue.take(); //blocks the thread until new request in queue
				executeRequest(request);
			}catch (InterruptedException e){
				Log.d(NetworkUtilities.TAG, e.toString());
				Thread.currentThread().interrupt();
			}
		}
	}

	/**
	 * Adds the request to a queue. The queue is processed by a thread.
	 *
	 * @param message
	 */
	public synchronized void handleRequest(NetworkMessage message){
		try{
			Log.d(NetworkUtilities.TAG, "Handle request called");
			queue.put(message);
			Log.d(NetworkUtilities.TAG, "New request added to RequestHandler queue");
		}catch (InterruptedException e){
			Log.d(NetworkUtilities.TAG, e.toString());
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Takes the request type and possible serialized Java objects from the request and then
	 * calls the appropriate server method to process the request.
	 *
	 * @param request
	 */
	private void executeRequest(NetworkMessage request){
		Log.d(NetworkUtilities.TAG, "Executing of request started");
		if (request instanceof LeaveLobbyNetworkMessage){
			gameActionProcessor.leaveLobby((LeaveLobbyNetworkMessage) request);
		}else if (request instanceof JoinLobbyNetworkMessage){
			gameActionProcessor.joinLobby((JoinLobbyNetworkMessage) request);
		}else if (request instanceof ReadyLobbyNetworkMessage){
			gameActionProcessor.changeReadyLobby((ReadyLobbyNetworkMessage) request);
		}else if (request instanceof ChangeGamePieceNetworkMessage){
			gameActionProcessor.changeGamePiece((ChangeGamePieceNetworkMessage) request);
		}
		// TODO: Use the classes of the request and the type to call the specific action method
	}

	private class GameActionProcessor implements GameActions{

		@Override
		public void joinLobby(JoinLobbyNetworkMessage message){
			LobbyPlayer player = new LobbyPlayer(message.getSenderName(), message.getSenderAddress(), message.getSenderPort(), message.getGamePiece(), false);
			Lobby.getInstance().addPlayer(player);
			Lobby.getInstance().calculateReadyState();
			LobbyResponse lobbyResponse = new LobbyResponse();
			lobbyResponse.setLobby(Lobby.getInstance());
			Lobby.getInstance().notifyListeners();
			GameServer.getInstance().sendResponseToAll(lobbyResponse);
		}

		@Override
		public void leaveLobby(LeaveLobbyNetworkMessage message){
			LobbyPlayer lobbyPlayer = null;
			for (LobbyPlayer player : Lobby.getInstance().getPlayers()){
				if (player.getAddress().equals(message.getSenderAddress()) && player.getPort() == message.getSenderPort()){
					lobbyPlayer = player;
					break;
				}
			}
			if (lobbyPlayer != null){
				Lobby.getInstance().removePlayer(lobbyPlayer);
			}
			Lobby.getInstance().calculateReadyState();
			LobbyResponse lobbyResponse = new LobbyResponse();
			lobbyResponse.setLobby(Lobby.getInstance());
			Lobby.getInstance().notifyListeners();
			GameServer.getInstance().sendResponseToAll(lobbyResponse);
		}

		@Override
		public void changeGamePiece(ChangeGamePieceNetworkMessage message){
			LobbyPlayer lobbyPlayer = null;
			for (LobbyPlayer player : Lobby.getInstance().getPlayers()){
				if (player.getAddress().equals(message.getSenderAddress()) && player.getPort() == message.getSenderPort()){
					lobbyPlayer = player;
					break;
				}
			}
			if (lobbyPlayer != null){
				Lobby.getInstance().changeGamePieceOfPlayer(lobbyPlayer, message.getGamePiece());
			}
			Lobby.getInstance().calculateReadyState();
			LobbyResponse lobbyResponse = new LobbyResponse();
			lobbyResponse.setLobby(Lobby.getInstance());
			Lobby.getInstance().notifyListeners();
			GameServer.getInstance().sendResponseToAll(lobbyResponse);
		}

		@Override
		public void changeReadyLobby(ReadyLobbyNetworkMessage message){
			for (LobbyPlayer player : Lobby.getInstance().getPlayers()){
				if (player.getAddress().equals(message.getSenderAddress()) && player.getPort() == message.getSenderPort()){
					player.setReady(message.isValue());
					break;
				}
			}
			Lobby.getInstance().calculateReadyState();
			LobbyResponse lobbyResponse = new LobbyResponse();
			lobbyResponse.setLobby(Lobby.getInstance());
			Lobby.getInstance().notifyListeners();
			GameServer.getInstance().sendResponseToAll(lobbyResponse);
		}

		@Override
		public void rollDice(){
			throw new UnsupportedOperationException();
		}

		@Override
		public void skipTurn(){
			throw new UnsupportedOperationException();
		}

		@Override
		public void buyDeed(Deed deed){
			throw new UnsupportedOperationException();
		}

		@Override
		public void sellDeed(Deed deed){
			throw new UnsupportedOperationException();
		}

		@Override
		public void buyHouse(Deed deed){
			throw new UnsupportedOperationException();
		}

		@Override
		public void sellHouse(Deed deed){
			throw new UnsupportedOperationException();
		}

		@Override
		public void buyHotel(Deed deed){
			throw new UnsupportedOperationException();
		}

		@Override
		public void sellHotel(Deed deed){
			throw new UnsupportedOperationException();
		}

		@Override
		public void raiseMortgage(Deed deed){
			throw new UnsupportedOperationException();
		}

		@Override
		public void redeemMortgage(Deed deed){
			throw new UnsupportedOperationException();
		}

		@Override
		public void tradeDeed(Deed deed, Player player){
			throw new UnsupportedOperationException();
		}

		@Override
		public void bidAtAuction(int amount){
			throw new UnsupportedOperationException();
		}

		@Override
		public void cheat(){
			throw new UnsupportedOperationException();
		}
	}
}
