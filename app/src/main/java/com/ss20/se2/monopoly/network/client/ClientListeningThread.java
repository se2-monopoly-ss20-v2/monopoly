package com.ss20.se2.monopoly.network.client;

import android.util.Log;

import com.google.gson.JsonParser;
import com.ss20.se2.monopoly.network.NetworkUtilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
				BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
				String inputLine;
				while ((inputLine = in.readLine()) != null){
					Log.d(NetworkUtilities.TAG, "Client thread received message: " + inputLine + " from Server");
					ResponseHandler.getInstance().handleRequest(JsonParser.parseString(inputLine).getAsJsonObject());
				}
			}catch (IOException e){
				Log.d(NetworkUtilities.TAG, e.toString());
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