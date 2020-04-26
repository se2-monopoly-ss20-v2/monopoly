package com.ss20.se2.monopoly.network.client;

import android.util.Log;

import com.google.gson.JsonObject;
import com.ss20.se2.monopoly.models.GamePiece;
import com.ss20.se2.monopoly.models.OnGameDataChangedListener;
import com.ss20.se2.monopoly.models.Player;
import com.ss20.se2.monopoly.models.fields.deeds.Deed;
import com.ss20.se2.monopoly.network.NetworkUtilities;
import com.ss20.se2.monopoly.network.shared.GameActions;
import com.ss20.se2.monopoly.network.shared.RequestType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GameController implements Runnable, GameActions{

	private int port;
	private InetAddress address;
	private static GameController instance;
	private Socket socket;
	private ClientToServerCommunicator communicator;
	private Thread connectingThread;
	private List<OnGameDataChangedListener> onGameDataChangedListeners;

	private GameController(){
		this.onGameDataChangedListeners = new ArrayList<>();
	}

	public static GameController getInstance(){
		if (instance == null){
			instance = new GameController();
		}
		return instance;
	}

	public void waitForEstablishedConnection() throws InterruptedException{
		connectingThread.join();
	}

	@Override
	public void run(){
		Log.d(NetworkUtilities.TAG, "Client connection thread Starting");
		try{
			socket = new Socket(address, port);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String message = in.readLine();
			if (message.equals("locked")){
				Log.d(NetworkUtilities.TAG, "Client cant connect because Server does not allow join");
			}else if (message.equals("full")){
				Log.d(NetworkUtilities.TAG, "Client cant connect because Server full");
			}else if (message.equals("ok")){
				communicator = new ClientToServerCommunicator(socket);
				Log.d(NetworkUtilities.TAG, "Client connection ok");
			}
		}catch (IOException e){
			Log.d(NetworkUtilities.TAG, e.toString());
		}
	}

	@Override
	public void joinGame(InetAddress address, int port){
		this.port = port;
		this.address = address;
		connectingThread = new Thread(this);
		connectingThread.start();
	}

	@Override
	public void leaveGame(){
		try{
			communicator.getSocket().close();
		}catch (IOException e){
			Log.d(NetworkUtilities.TAG, e.toString());
		}
	}

	public void addOnGameDataChangedListener(OnGameDataChangedListener listener){
		onGameDataChangedListeners.add(listener);
	}

	public List<OnGameDataChangedListener> getOnGameDataChangedListeners(){
		return onGameDataChangedListeners;
	}

	@Override
	public void changeGamePiece(Player player, GamePiece gamePiece){
		JsonObject requestObject = buildJSONRequestObject(RequestType.CHANGE_GAME_PIECE, player, gamePiece);
		communicator.sendMessage(requestObject);
	}

	@Override
	public void rollDice(){
		JsonObject requestObject = buildJSONRequestObject(RequestType.ROLL_DICE);
		communicator.sendMessage(requestObject);
	}

	@Override
	public void skipTurn(){
		JsonObject requestObject = buildJSONRequestObject(RequestType.SKIP_TURN);
		communicator.sendMessage(requestObject);
	}

	@Override
	public void buyDeed(Deed deed){
		JsonObject requestObject = buildJSONRequestObject(RequestType.BUY_DEED, deed);
		communicator.sendMessage(requestObject);
	}

	@Override
	public void sellDeed(Deed deed){
		JsonObject requestObject = buildJSONRequestObject(RequestType.SELL_DEED, deed);
		communicator.sendMessage(requestObject);
	}

	@Override
	public void buyHouse(Deed deed){
		JsonObject requestObject = buildJSONRequestObject(RequestType.BUY_HOUSE, deed);
		communicator.sendMessage(requestObject);
	}

	@Override
	public void sellHouse(Deed deed){
		JsonObject requestObject = buildJSONRequestObject(RequestType.SELL_HOUSE, deed);
		communicator.sendMessage(requestObject);
	}

	@Override
	public void buyHotel(Deed deed){
		JsonObject requestObject = buildJSONRequestObject(RequestType.BUY_HOTEL, deed);
		communicator.sendMessage(requestObject);
	}

	@Override
	public void sellHotel(Deed deed){
		JsonObject requestObject = buildJSONRequestObject(RequestType.SELL_HOTEL, deed);
		communicator.sendMessage(requestObject);
	}

	@Override
	public void raiseMortgage(Deed deed){
		JsonObject requestObject = buildJSONRequestObject(RequestType.RAISE_MORTGAGE, deed);
		communicator.sendMessage(requestObject);
	}

	@Override
	public void redeemMortgage(Deed deed){
		JsonObject requestObject = buildJSONRequestObject(RequestType.REDEEM_MORTGAGE, deed);
		communicator.sendMessage(requestObject);
	}

	@Override
	public void tradeDeed(Deed deed, Player player){
		JsonObject requestObject = buildJSONRequestObject(RequestType.TRADE_DEED, deed, player);
		communicator.sendMessage(requestObject);
	}

	@Override
	public void bidAtAuction(int amount){
		JsonObject requestObject = buildJSONRequestObject(RequestType.SELL_DEED, amount);
		communicator.sendMessage(requestObject);
	}

	@Override
	public void cheat(){
		JsonObject requestObject = buildJSONRequestObject(RequestType.CHEAT);
		communicator.sendMessage(requestObject);
	}

	public JsonObject buildJSONRequestObject(RequestType identificationCode, Object... parameterClass){
		JsonObject object = new JsonObject();
		String type = RequestType.toString(identificationCode);
		object.addProperty("type", type);
		// TODO: Insert objects into the request (and add the request type) so that it can be
		//  de-serialized on the server side
		return object;
	}
}