package com.ss20.se2.monopoly.network.shared;

import android.util.Log;

import com.google.gson.JsonObject;
import com.ss20.se2.monopoly.network.NetworkUtilities;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

public class SendingThread implements Runnable{

	private List<OutputStream> outputStreamList;
	private JsonObject jsonObject;

	public SendingThread(List<OutputStream> outputStreamList){
		this.outputStreamList = outputStreamList;
	}

	@Override
	public void run(){
		Log.d(NetworkUtilities.TAG, "Communication thread starting");
		for (OutputStream outStream : outputStreamList){
			PrintWriter out = new PrintWriter(outStream, true);
			Log.d(NetworkUtilities.TAG, "Sent " + jsonObject);
			out.println(jsonObject);
		}
	}

	public JsonObject getJsonObject(){
		return jsonObject;
	}

	public void setJsonObject(JsonObject jsonObject){
		this.jsonObject = jsonObject;
	}

	public void setOutputStreamList(List<OutputStream> outputStreamList){
		this.outputStreamList = outputStreamList;
	}
}