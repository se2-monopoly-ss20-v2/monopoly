package com.ss20.se2.monopoly.network.client;

import android.util.Log;

import com.ss20.se2.monopoly.models.Lobby;
import com.ss20.se2.monopoly.network.NetworkUtilities;
import com.ss20.se2.monopoly.network.server.NetworkResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

class ClientListeningThread implements Runnable{

	private InputStream inputStream;
	private boolean running;

	public ClientListeningThread(InputStream inputStream){
		this.inputStream = inputStream;
	}

	@Override
	public void run(){
		Log.d(NetworkUtilities.TAG, "Client thread Starting");
		while (running){
			try{
				ObjectInputStream in = new ObjectInputStream(inputStream);
				NetworkResponse response;
				while ((response = (NetworkResponse) in.readObject()) != null){
					Log.d(NetworkUtilities.TAG, "Client thread received message: " + response + " from Server");
					ResponseHandler.getInstance().handleRequest(response);
				}
			}catch (Exception e){
				if (Lobby.getInstance().isActive()){
					Lobby.getInstance().closeLobby();
				}
				Log.e(NetworkUtilities.TAG, e.toString());
				running = false;
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