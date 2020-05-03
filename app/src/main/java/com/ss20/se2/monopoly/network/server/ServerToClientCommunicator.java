package com.ss20.se2.monopoly.network.server;

import com.google.gson.JsonObject;
import com.ss20.se2.monopoly.network.shared.SendingThread;

import java.io.IOException;
import java.net.Socket;
import java.util.Collections;

class ServerToClientCommunicator{

	private SendingThread sendingThread;
	private ServerListeningThread listeningThread;
	private Socket socket;

	public ServerToClientCommunicator(Socket socket) throws IOException{
		this.socket = socket;
		sendingThread = new SendingThread(Collections.singletonList(socket.getOutputStream()));
		listeningThread = new ServerListeningThread(socket.getInputStream(), socket);
		new Thread(listeningThread).start();
	}

	public void sendMessage(NetworkResponse object){
		sendingThread.setObject(object);
		new Thread(sendingThread).start();
	}

	public void closeListeningThread() throws IOException{
		listeningThread.stop();
	}

	public Socket getSocket(){
		return socket;
	}
}
