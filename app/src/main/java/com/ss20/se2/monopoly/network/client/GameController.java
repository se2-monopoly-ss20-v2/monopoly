package com.ss20.se2.monopoly.network.client;

import android.util.Log;

import com.ss20.se2.monopoly.models.Lobby;
import com.ss20.se2.monopoly.models.OnGameDataChangedListener;
import com.ss20.se2.monopoly.models.Player;
import com.ss20.se2.monopoly.models.fields.deeds.Deed;
import com.ss20.se2.monopoly.network.GameStateNetworkMessage;
import com.ss20.se2.monopoly.network.NetworkUtilities;
import com.ss20.se2.monopoly.network.shared.GameActions;

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
	private boolean joined;

	private GameController(){
		this.onGameDataChangedListeners = new ArrayList<>();
		this.joined = false;
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
			switch (message){
				case "locked":
					Log.d(NetworkUtilities.TAG, "Client cant connect because Server does not allow join");
					break;
				case "full":
					Log.d(NetworkUtilities.TAG, "Client cant connect because Server full");
					break;
				case "ok":
					communicator = new ClientToServerCommunicator(socket);
					joined = true;
					Log.d(NetworkUtilities.TAG, "Client connection ok");
					break;
			}
		}catch (IOException e){
			Log.d(NetworkUtilities.TAG, e.toString());
		}
	}

	public void joinGame(InetAddress address, int port){
		this.port = port;
		this.address = address;
		connectingThread = new Thread(this);
		connectingThread.start();
	}

	public void leaveGame(){
		try{
			communicator.waitForSendingThread();
			communicator.getSocket().close();
			communicator = null;
			joined = false;
		}catch (Exception e){
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
	public void joinLobby(JoinLobbyNetworkMessage message){
		communicator.sendMessage(message);
	}

	@Override
	public void leaveLobby(LeaveLobbyNetworkMessage message){
		communicator.sendMessage(message);
	}

	@Override
	public void changeGamePiece(ChangeGamePieceNetworkMessage message){
		communicator.sendMessage(message);
	}

	@Override
	public void changeReadyLobby(ReadyLobbyNetworkMessage message){
		boolean newVal = !(Lobby.getInstance().getSelf().isReady());
		Lobby.getInstance().getSelf().setReady(newVal);
		message.setValue(newVal);
		communicator.sendMessage(message);
	}

	@Override
	public void rollDice(){
		communicator.sendMessage(null);
	}

	@Override
	public void skipTurn(){
		communicator.sendMessage(null);
	}

	@Override
	public void buyDeed(Deed deed){
		communicator.sendMessage(null);
	}

	@Override
	public void sellDeed(Deed deed){
		communicator.sendMessage(null);
	}

	@Override
	public void buyHouse(Deed deed){
		communicator.sendMessage(null);
	}

	@Override
	public void sellHouse(Deed deed){
		communicator.sendMessage(null);
	}

	@Override
	public void buyHotel(Deed deed){
		communicator.sendMessage(null);
	}

	@Override
	public void sellHotel(Deed deed){
		communicator.sendMessage(null);
	}

	@Override
	public void raiseMortgage(Deed deed){
		communicator.sendMessage(null);
	}

	@Override
	public void redeemMortgage(Deed deed){
		communicator.sendMessage(null);
	}

	@Override
	public void tradeDeed(Deed deed, Player player){
		communicator.sendMessage(null);
	}

	@Override
	public void bidAtAuction(int amount){
		communicator.sendMessage(null);
	}

	@Override
	public void cheat(){
		communicator.sendMessage(null);
	}

	@Override
	public void setupGameState(GameStateNetworkMessage message){
		communicator.sendMessage(message);
	}

	public Socket getSocket(){
		return socket;
	}

	public boolean isJoined(){
		return joined;
	}

	public void setSocket(Socket socket){
		this.socket = socket;
	}
}