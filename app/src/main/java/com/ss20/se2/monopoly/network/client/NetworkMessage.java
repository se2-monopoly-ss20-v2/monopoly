package com.ss20.se2.monopoly.network.client;

import com.ss20.se2.monopoly.network.shared.NetworkObject;
import com.ss20.se2.monopoly.network.shared.RequestType;

import java.io.Serializable;
import java.net.InetAddress;

public class NetworkMessage extends NetworkObject implements Serializable{
	private RequestType type;
	private String senderName;
	private InetAddress senderAddress;
	private int senderPort;

	public RequestType getType(){
		return type;
	}

	public void setType(RequestType type){
		this.type = type;
	}

	public String getSenderName(){
		return senderName;
	}

	public void setSenderName(String senderName){
		this.senderName = senderName;
	}

	public InetAddress getSenderAddress(){
		return senderAddress;
	}

	public void setSenderAddress(InetAddress senderAddress){
		this.senderAddress = senderAddress;
	}

	public int getSenderPort(){
		return senderPort;
	}

	public void setSenderPort(int senderPort){
		this.senderPort = senderPort;
	}
}
