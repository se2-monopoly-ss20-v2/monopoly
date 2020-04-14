package com.ss20.se2.monopoly.pojo;

import java.net.InetAddress;

public class LocalGame{

	private InetAddress address;
	private int port;

	public LocalGame(InetAddress address, int port){
		this.address = address;
		this.port = port;
	}

	public InetAddress getAddress(){
		return address;
	}

	public void setAddress(InetAddress address){
		this.address = address;
	}

	public int getPort(){
		return port;
	}

	public void setPort(int port){
		this.port = port;
	}
}
