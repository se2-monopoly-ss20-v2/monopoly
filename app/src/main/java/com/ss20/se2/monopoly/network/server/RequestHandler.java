package com.ss20.se2.monopoly.network.server;

import android.util.Log;

import com.google.gson.JsonObject;
import com.ss20.se2.monopoly.models.GamePiece;
import com.ss20.se2.monopoly.models.Player;
import com.ss20.se2.monopoly.models.fields.deeds.Deed;
import com.ss20.se2.monopoly.network.NetworkUtilities;
import com.ss20.se2.monopoly.network.shared.GameActions;
import com.ss20.se2.monopoly.network.shared.ResponseType;

import java.net.InetAddress;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

class RequestHandler implements Runnable{

	private final AtomicBoolean running = new AtomicBoolean(false);
	private static RequestHandler instance;
	private GameActionProcessor gameActionProcessor;
	private BlockingQueue<JsonObject> queue;

	private RequestHandler(){
		this.queue = new LinkedBlockingDeque<>();
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
				JsonObject request = queue.take(); //blocks the thread until new request in queue
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
	 * @param request
	 */
	synchronized void handleRequest(JsonObject request){
		try{
			Log.d(NetworkUtilities.TAG, "Handle request called");
			queue.put(request);
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
	private void executeRequest(JsonObject request){
		Log.d(NetworkUtilities.TAG, "Executing of request started");
		// TODO: Use the classes of the request and the type to call the specific action method
	}

	private class GameActionProcessor implements GameActions{

		@Override
		public void joinGame(InetAddress address, int port){
			// Add here individual functionality to game method and update server database
			// ...
			JsonObject response = new JsonObject();
			String responseType = ResponseType.toString(ResponseType.GAME_JOINED);
			response.addProperty("type", responseType);
			// Gather the relevant changes and build the response
			// ...
			GameServer.getInstance().sendResponseToAll(response);
		}

		@Override
		public void leaveGame(){
			// Add here individual functionality to game method and update server database
			// ...
			JsonObject response = new JsonObject();
			String responseType = ResponseType.toString(ResponseType.GAME_LEFT);
			response.addProperty("type", responseType);
			// Gather the relevant changes and build the response
			// ...
			GameServer.getInstance().sendResponseToAll(response);
		}

		@Override
		public void changeGamePiece(Player player, GamePiece gamePiece){
			// Add here individual functionality to game method and update server database
			// ...
			JsonObject response = new JsonObject();
			String responseType = ResponseType.toString(ResponseType.GAME_PIECE_CHANGED); // or GAME_PIECE_NOT_CHANGED (For example, if double)
			response.addProperty("type", responseType);
			// Gather the relevant changes and build the response
			// ...
			GameServer.getInstance().sendResponseToAll(response);
		}

		@Override
		public void rollDice(){
			// Add here individual functionality to game method and update server database
			// ...
			JsonObject response = new JsonObject();
			String responseType = ResponseType.toString(ResponseType.DICE_ROLLED); // or DICE_NOT_ROLLED
			response.addProperty("type", responseType);
			// Gather the relevant changes and build the response
			// ...
			GameServer.getInstance().sendResponseToAll(response);
		}

		@Override
		public void skipTurn(){
			// Add here individual functionality to game method and update server database
			// ...
			JsonObject response = new JsonObject();
			String responseType = ResponseType.toString(ResponseType.TURN_SKIPPED);
			response.addProperty("type", responseType);
			// Gather the relevant changes and build the response
			// ...
			GameServer.getInstance().sendResponseToAll(response);
		}

		@Override
		public void buyDeed(Deed deed){
			// Add here individual functionality to game method and update server database
			// ...
			JsonObject response = new JsonObject();
			String responseType = ResponseType.toString(ResponseType.DEED_BOUGHT);
			response.addProperty("type", responseType);
			// Gather the relevant changes and build the response
			// ...
			GameServer.getInstance().sendResponseToAll(response);
		}

		@Override
		public void sellDeed(Deed deed){
			// Add here individual functionality to game method and update server database
			// ...
			JsonObject response = new JsonObject();
			String responseType = ResponseType.toString(ResponseType.DEED_SOLD);
			response.addProperty("type", responseType);
			// Gather the relevant changes and build the response
			// ...
			GameServer.getInstance().sendResponseToAll(response);
		}

		@Override
		public void buyHouse(Deed deed){
			// Add here individual functionality to game method and update server database
			// ...
			JsonObject response = new JsonObject();
			String responseType = ResponseType.toString(ResponseType.HOUSE_BOUGHT);
			response.addProperty("type", responseType);
			// Gather the relevant changes and build the response
			// ...
			GameServer.getInstance().sendResponseToAll(response);
		}

		@Override
		public void sellHouse(Deed deed){
			// Add here individual functionality to game method and update server database
			// ...
			JsonObject response = new JsonObject();
			String responseType = ResponseType.toString(ResponseType.HOUSE_SOLD);
			response.addProperty("type", responseType);
			// Gather the relevant changes and build the response
			// ...
			GameServer.getInstance().sendResponseToAll(response);
		}

		@Override
		public void buyHotel(Deed deed){
			// Add here individual functionality to game method and update server database
			// ...
			JsonObject response = new JsonObject();
			String responseType = ResponseType.toString(ResponseType.HOTEL_BOUGHT);
			response.addProperty("type", responseType);
			// Gather the relevant changes and build the response
			// ...
			GameServer.getInstance().sendResponseToAll(response);
		}

		@Override
		public void sellHotel(Deed deed){
			// Add here individual functionality to game method and update server database
			// ...
			JsonObject response = new JsonObject();
			String responseType = ResponseType.toString(ResponseType.HOTEL_SOLD);
			response.addProperty("type", responseType);
			// Gather the relevant changes and build the response
			// ...
			GameServer.getInstance().sendResponseToAll(response);
		}

		@Override
		public void raiseMortgage(Deed deed){
			// Add here individual functionality to game method and update server database
			// ...
			JsonObject response = new JsonObject();
			String responseType = ResponseType.toString(ResponseType.MORTGAGE_NOT_RAISED);
			response.addProperty("type", responseType);
			// Gather the relevant changes and build the response
			// ...
			GameServer.getInstance().sendResponseToAll(response);
		}

		@Override
		public void redeemMortgage(Deed deed){
			// Add here individual functionality to game method and update server database
			// ...
			JsonObject response = new JsonObject();
			String responseType = ResponseType.toString(ResponseType.MORTGAGE_REDEEMED);
			response.addProperty("type", responseType);
			// Gather the relevant changes and build the response
			// ...
			GameServer.getInstance().sendResponseToAll(response);
		}

		@Override
		public void tradeDeed(Deed deed, Player player){
			// Add here individual functionality to game method and update server database
			// ...
			JsonObject response = new JsonObject();
			String responseType = ResponseType.toString(ResponseType.DEED_TRADED);
			response.addProperty("type", responseType);
			// Gather the relevant changes and build the response
			// ...
			GameServer.getInstance().sendResponseToAll(response);
		}

		@Override
		public void bidAtAuction(int amount){
			// Add here individual functionality to game method and update server database
			// ...
			JsonObject response = new JsonObject();
			String responseType = ResponseType.toString(ResponseType.BIDDEN_ON_AUCTION);
			response.addProperty("type", responseType);
			// Gather the relevant changes and build the response
			// ...
			GameServer.getInstance().sendResponseToAll(response);
		}

		@Override
		public void cheat(){
			// Add here individual functionality to game method and update server database
			// ...
			JsonObject response = new JsonObject();
			String responseType = ResponseType.toString(ResponseType.CHEATED); // cheating can actually be any type
			response.addProperty("type", responseType);
			// Gather the relevant changes and build the response
			// ...
			GameServer.getInstance().sendResponseToAll(response);
		}
	}
}
