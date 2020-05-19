package com.ss20.se2.monopoly.network.server;

import com.ss20.se2.monopoly.models.Lobby;

import java.io.Serializable;

public class LobbyResponse extends NetworkResponse implements Serializable{
	Lobby lobby;

	public Lobby getLobby(){
		return lobby;
	}

	public void setLobby(Lobby lobby){
		this.lobby = lobby;
	}
}
