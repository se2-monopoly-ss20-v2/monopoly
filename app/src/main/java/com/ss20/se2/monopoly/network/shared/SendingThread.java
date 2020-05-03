package com.ss20.se2.monopoly.network.shared;

import android.util.Log;

import com.ss20.se2.monopoly.network.NetworkUtilities;
import com.ss20.se2.monopoly.network.client.NetworkMessage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.List;

public class SendingThread implements Runnable{

	private List<OutputStream> outputStreamList;
	private NetworkObject object;

	public SendingThread(List<OutputStream> outputStreamList){
		this.outputStreamList = outputStreamList;
	}

	@Override
	public void run(){
		Log.d(NetworkUtilities.TAG, "Communication thread starting");
		for (OutputStream outStream : outputStreamList){
			ObjectOutputStream out = null;
			try{
				out = new ObjectOutputStream(outStream);
				Log.d(NetworkUtilities.TAG, "Sent " + object);
				out.writeObject(object);
			}catch (IOException e){
				e.printStackTrace();
			}
		}
	}

	public NetworkObject getObject(){
		return object;
	}

	public void setObject(NetworkObject object){
		this.object = object;
	}

	public void setOutputStreamList(List<OutputStream> outputStreamList){
		this.outputStreamList = outputStreamList;
	}
}