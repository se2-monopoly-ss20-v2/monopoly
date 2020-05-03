package com.ss20.se2.monopoly.network.client;

import com.ss20.se2.monopoly.network.shared.SendingThread;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Collections;

class ClientToServerCommunicator{

	private SendingThread sendingThread;
	private Thread sendingThreadObject;
	private ClientListeningThread listeningThread;
	private Socket socket;

	public ClientToServerCommunicator(Socket socket) throws IOException{
		this.socket = socket;
		sendingThread = new SendingThread(Collections.singletonList(new ObjectOutputStream(socket.getOutputStream())));
		listeningThread = new ClientListeningThread(socket.getInputStream());
		listeningThread.setRunning(true);
		new Thread(listeningThread).start();
	}

	public void sendMessage(NetworkMessage message){
		sendingThread.setObject(message);
		sendingThreadObject = new Thread(sendingThread);
		sendingThreadObject.start();
	}

	public void closeListeningThread() throws IOException{
		listeningThread.stop();
	}

	public void waitForSendingThread() throws InterruptedException{
		sendingThreadObject.join();
	}

	public Socket getSocket(){
		return socket;
	}

	public SendingThread getSendingThread(){
		return sendingThread;
	}
}
