package com.ss20.se2.monopoly.network.client;

import com.google.gson.JsonObject;
import com.ss20.se2.monopoly.network.shared.SendingThread;

import java.io.IOException;
import java.net.Socket;
import java.util.Collections;

class ClientToServerCommunicator{

	private SendingThread sendingThread;
	private ClientListeningThread listeningThread;
	private Socket socket;

	public ClientToServerCommunicator(Socket socket) throws IOException{
		this.socket = socket;
		sendingThread = new SendingThread(Collections.singletonList(socket.getOutputStream()));
		listeningThread = new ClientListeningThread(socket.getInputStream());
		listeningThread.setRunning(true);
		new Thread(listeningThread).start();
	}

	public void sendMessage(JsonObject object){
		sendingThread.setJsonObject(object);
		new Thread(sendingThread).start();
	}

	public void closeListeningThread() throws IOException{
		listeningThread.stop();
	}

	public Socket getSocket(){
		return socket;
	}
}
