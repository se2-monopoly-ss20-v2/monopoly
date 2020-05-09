package com.ss20.se2.monopoly.network.client;

import android.util.Log;

import com.ss20.se2.monopoly.models.GamePiece;
import com.ss20.se2.monopoly.models.Lobby;
import com.ss20.se2.monopoly.models.LobbyPlayer;
import com.ss20.se2.monopoly.models.Player;
import com.ss20.se2.monopoly.models.fields.deeds.Deed;
import com.ss20.se2.monopoly.network.NetworkUtilities;
import com.ss20.se2.monopoly.network.server.GameServer;
import com.ss20.se2.monopoly.network.server.LobbyResponse;
import com.ss20.se2.monopoly.network.server.NetworkResponse;
import com.ss20.se2.monopoly.network.shared.GameActions;
import com.ss20.se2.monopoly.network.shared.GameResponses;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

class ResponseHandler implements Runnable{

	private final AtomicBoolean running = new AtomicBoolean(false);
	private static ResponseHandler instance;
	private GameActionProcessor gameActionProcessor;
	private BlockingQueue<NetworkResponse> queue;

	private ResponseHandler(){
		this.queue = new LinkedBlockingDeque<NetworkResponse>();
		gameActionProcessor = new GameActionProcessor();
		start();
	}

	/**
	 * Returns a singleton instance of this class
	 *
	 * @return
	 */
	static ResponseHandler getInstance(){
		if (instance == null){
			instance = new ResponseHandler();
		}
		return instance;
	}

	private void start(){
		running.set(true);
		new Thread(this).start();
	}

	private void stop(){
		running.set(false);
	}

	@Override
	public void run(){
		while (running.get()){
			try{
				NetworkResponse response = queue.take(); //blocks the thread until new response in queue
				processResponse(response);
			}catch (InterruptedException e){
				Log.d(NetworkUtilities.TAG, e.toString());
				Thread.currentThread().interrupt();
			}
		}
	}

	/**
	 * Adds the response to a queue. The queue is processed by a thread.
	 *
	 * @param response
	 */
	public synchronized void handleRequest(NetworkResponse response){
		try{
			Log.d(NetworkUtilities.TAG, "Handle response called");
			queue.put(response);
			Log.d(NetworkUtilities.TAG, "New response added to ResponseHandler queue");
		}catch (InterruptedException e){
			Log.d(NetworkUtilities.TAG, e.toString());
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Takes the response type and possible serialized Java objects from the response and
	 * updates the database. After that this method notifies all listeners who have subscribed to changes.
	 * Only methods of the listeners that are relevant are called. The shortly before changed data in
	 * the database is  taken from there and added to the relevant methods as parameters.
	 *
	 * @param response
	 */
	private void processResponse(NetworkResponse response){
		Log.d(NetworkUtilities.TAG, "Processing of response started");
		if (response instanceof LobbyResponse){
			gameActionProcessor.lobby((LobbyResponse) response);
		}
		// TODO: First update database and then call the appropriate method(s) (using the response type)
		//  of the OnGameDataChangedListener for every type
	}

	private class GameActionProcessor implements GameResponses{

		@Override
		public void lobby(LobbyResponse response){
			Lobby.getInstance().setPlayers(response.getLobby().getPlayers());
			Lobby.getInstance().setReady(response.getLobby().isReady());
			Lobby.getInstance().setStarted(response.getLobby().isStarted());
			Lobby.getInstance().notifyListeners();
		}
	}
}
