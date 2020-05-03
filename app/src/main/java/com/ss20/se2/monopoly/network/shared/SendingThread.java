package com.ss20.se2.monopoly.network.shared;

import android.util.Log;

import com.ss20.se2.monopoly.network.NetworkUtilities;
import com.ss20.se2.monopoly.network.client.NetworkMessage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.List;

public class SendingThread implements Runnable{

	private List<ObjectOutputStream> outputStreamList;
	private NetworkObject object;

	public SendingThread(List<ObjectOutputStream> outputStreamList){
		this.outputStreamList = outputStreamList;
	}

	@Override
	public void run(){
		Log.d(NetworkUtilities.TAG, "Communication thread starting");
		for (ObjectOutputStream outStream : outputStreamList){
			try{
				Log.d(NetworkUtilities.TAG, "Sent " + object);
				outStream.writeUnshared(object);
				outStream.reset();
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

	public void setOutputStreamList(List<ObjectOutputStream> outputStreamList){
		this.outputStreamList = outputStreamList;
	}
}