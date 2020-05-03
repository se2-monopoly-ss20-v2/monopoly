package com.ss20.se2.monopoly.models;

import com.ss20.se2.monopoly.models.fields.cards.Card;
import com.ss20.se2.monopoly.models.fields.deeds.Deed;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class LobbyPlayer implements Serializable{

	private String name;
	private InetAddress address;
	private int port;
	private GamePiece gamePiece;
	private boolean ready;

	public LobbyPlayer(String name, InetAddress address, int port, GamePiece gamePiece){
		this.name = name;
		this.address = address;
		this.port = port;
		this.gamePiece = gamePiece;
		this.ready = false;
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
}
