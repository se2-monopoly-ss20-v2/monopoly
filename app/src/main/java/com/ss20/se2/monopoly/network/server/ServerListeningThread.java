package com.ss20.se2.monopoly.network.server;

import android.util.Log;

import com.google.gson.JsonParser;
import com.ss20.se2.monopoly.network.NetworkUtilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

class ServerListeningThread implements Runnable{

	private InputStream inputStream;
	private boolean running = true;

	public ServerListeningThread(InputStream inputStream){
		this.inputStream = inputStream;
	}

	@Override
	public void run(){
		Log.d(NetworkUtilities.TAG, "Server thread starting");
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
		String inputLine;
		while (running){
			try{
				while ((inputLine = in.readLine()) != null){
					Log.d(NetworkUtilities.TAG, "Server thread received message: " + inputLine + " from " + inputStream.toString());
					RequestHandler.getInstance().handleRequest(JsonParser.parseString(inputLine).getAsJsonObject());
				}
			}catch (Exception e){
				running = false;
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