package com.ss20.se2.monopoly.network.server;

import android.content.Context;
import android.util.Log;

import com.ss20.se2.monopoly.models.GamePiece;
import com.ss20.se2.monopoly.models.Lobby;
import com.ss20.se2.monopoly.network.LocalGamePublisher;
import com.ss20.se2.monopoly.network.NetworkUtilities;
import com.ss20.se2.monopoly.network.client.ChangeGamePieceNetworkMessage;
import com.ss20.se2.monopoly.network.shared.RequestType;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GameServer implements Runnable{

	private ServerSocket serverSocket;
	private boolean joining;
	private InetAddress address;
	private int port;
	private static GameServer instance;
	private boolean running;
	private boolean discoveryRunning;
	private List<ServerToClientCommunicator> communicators;
	private static final String SERVER_NOT_RUNNING = "Game server is not running";
	private static final String SERVER_ALREADY_RUNNING = "Game server is already running";

	private GameServer(){
		communicators = Collections.synchronizedList(new LinkedList<ServerToClientCommunicator>());
	}

	public static GameServer getInstance(){
		if (instance == null){
			instance = new GameServer();
		}
		return instance;
	}

	public void startServer() throws IOException, GameServerAlreadyRunningException{
		if (!running){
			serverSocket = new ServerSocket(0);
			address = serverSocket.getInetAddress();
			port = serverSocket.getLocalPort();
			running = true;
			discoveryRunning = true;
			new Thread(this).start();
		}else{
			throw new GameServerAlreadyRunningException(SERVER_ALREADY_RUNNING);
		}
	}

	public void allowJoining(Context context) throws GameServerNotRunningException{
		if (running){
			LocalGamePublisher.getInstance().showGameInNetwork(context, port);
			joining = true;
		}else{
			throw new GameServerNotRunningException(SERVER_NOT_RUNNING);
		}
	}

	public void refuseJoining(Context context) throws GameServerNotRunningException{
		if (running){
			joining = false;
			LocalGamePublisher.getInstance().hideGameInNetwork(context);
		}else{
			throw new GameServerNotRunningException(SERVER_NOT_RUNNING);
		}
	}

	public void startGame(Context context) throws GameServerNotRunningException{
		if (running){
			refuseJoining(context);
			Lobby.getInstance().setStarted(true);
			LobbyResponse lobbyResponse = new LobbyResponse();
			lobbyResponse.setLobby(Lobby.getInstance());
			GameServer.getInstance().sendResponseToAll(lobbyResponse);
		}else{
			throw new GameServerNotRunningException(SERVER_NOT_RUNNING);
		}
	}

	public void shutdownServer(Context context) throws GameServerNotRunningException, IOException{
		if (running){
			refuseJoining(context);
			closeDiscovery();
			for (ServerToClientCommunicator communicator : communicators){
				communicator.getSocket().close();
			}
			communicators = Collections.synchronizedList(new LinkedList<ServerToClientCommunicator>());
			running = false;
		}else{
			throw new GameServerNotRunningException(SERVER_NOT_RUNNING);
		}
	}

	public InetAddress getAddress(){
		return address;
	}

	public int getPort(){
		return port;
	}

	private synchronized void addCommunicator(ServerToClientCommunicator communicator){
		communicators.add(communicator);
	}

	List<ServerToClientCommunicator> getCommunicators(){
		return communicators;
	}

	void sendResponseToAll(NetworkResponse response){
		for (ServerToClientCommunicator communicator : communicators){
			communicator.sendMessage(response);
		}
	}

	public int getNumberOfConnectedPlayers(){
		return communicators.size();
	}

	@Override
	public void run(){
		Log.d(NetworkUtilities.TAG, "Server starting");
		while (discoveryRunning){
			try{
				Socket socket = serverSocket.accept();
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				if (!joining){
					out.println("locked");
					Log.d(NetworkUtilities.TAG, "Server refused because joining not allowed");
				}else if (getNumberOfConnectedPlayers() + 1 >= NetworkUtilities.MAX_PLAYERS){
					out.println("full");
					Log.d(NetworkUtilities.TAG, "Server refused because full");
				}else{
					out.println("ok");
					ServerToClientCommunicator communicator = new ServerToClientCommunicator(socket);
					addCommunicator(communicator);
					Log.d(NetworkUtilities.TAG, "Server added Connection");
				}
			}catch (IOException e){
				Log.d(NetworkUtilities.TAG, e.toString());
				discoveryRunning = false;
				break;
			}
		}
		Log.d(NetworkUtilities.TAG, "Server connecting to clients done");
	}

	private void closeDiscovery(){
		discoveryRunning = false;
		try{
			serverSocket.close();
		}catch (IOException e){
			Log.e(NetworkUtilities.TAG, e.toString());
		}
		Log.i(NetworkUtilities.TAG, "Server closed discovery");
	}

	public void changeGamePiece(String name){
		ChangeGamePieceNetworkMessage networkMessage = new ChangeGamePieceNetworkMessage();
		networkMessage.setSenderName(Lobby.getInstance().getSelf().getName());
		networkMessage.setSenderAddress(Lobby.getInstance().getSelf().getAddress());
		networkMessage.setSenderPort(Lobby.getInstance().getSelf().getPort());
		networkMessage.setType(RequestType.CHANGE_GAME_PIECE);
		networkMessage.setGamePiece(new GamePiece(name));
		RequestHandler.getInstance().handleRequest(networkMessage);
	}
}