package com.ss20.se2.monopoly.network.server;

import android.util.Log;

import com.ss20.se2.monopoly.models.Lobby;
import com.ss20.se2.monopoly.network.NetworkUtilities;
import com.ss20.se2.monopoly.network.client.LeaveLobbyNetworkMessage;
import com.ss20.se2.monopoly.network.client.NetworkMessage;
import com.ss20.se2.monopoly.network.shared.RequestType;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

class ServerListeningThread implements Runnable{

	private InputStream inputStream;
	private boolean running = true;
	private Socket socket;

	public ServerListeningThread(InputStream inputStream, Socket socket){
		this.inputStream = inputStream;
		this.socket = socket;
	}

	@Override
	public void run(){
		Log.d(NetworkUtilities.TAG, "Server thread starting");
		ObjectInputStream in = null;
		try{
			in = new ObjectInputStream(inputStream);
		}catch (IOException e){
			Log.e(NetworkUtilities.TAG, e.getMessage());
		}
		NetworkMessage message;
		while (running){
			try{
				if (in != null){
					while ((message = (NetworkMessage) in.readObject()) != null){
						Log.d(NetworkUtilities.TAG, "Server thread received message: " + message + " from " + inputStream.toString());
						RequestHandler.getInstance().handleRequest(message);
					}
				}
			}catch (Exception e){
				running = false;
				if (Lobby.getInstance().isActive()){
					LeaveLobbyNetworkMessage networkMessage = new LeaveLobbyNetworkMessage();
					networkMessage.setSenderName("");
					networkMessage.setSenderAddress(socket.getInetAddress());
					networkMessage.setSenderPort(socket.getPort());
					networkMessage.setType(RequestType.LEAVE_GAME);
					RequestHandler.getInstance().handleRequest(networkMessage);
				}
				Log.d(NetworkUtilities.TAG, e.toString());
				break;
			}
		}
	}

	public void stop() throws IOException{
		running = false;
		inputStream.close();
	}

	public void setRunning(boolean running){
		this.running = running;
	}
}