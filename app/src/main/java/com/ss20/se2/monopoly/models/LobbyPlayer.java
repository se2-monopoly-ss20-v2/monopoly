package com.ss20.se2.monopoly.models;

import java.io.Serializable;
import java.net.InetAddress;

public class LobbyPlayer implements Serializable{

	private String name;
	private InetAddress address;
	private int port;
	private GamePiece gamePiece;
	private boolean ready;
	private boolean isHost;

	public LobbyPlayer(String name, InetAddress address, int port, GamePiece gamePiece, boolean isHost){
		this.name = name;
		this.address = address;
		this.port = port;
		this.gamePiece = gamePiece;
		this.ready = false;
		this.isHost = isHost;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
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

	public GamePiece getGamePiece(){
		return gamePiece;
	}

	public void setGamePiece(GamePiece gamePiece){
		this.gamePiece = gamePiece;
	}

	public boolean isReady(){
		return ready;
	}

	public void setReady(boolean ready){
		this.ready = ready;
	}

	public boolean isHost(){
		return isHost;
	}

	public void setHost(boolean host){
		isHost = host;
	}
}
