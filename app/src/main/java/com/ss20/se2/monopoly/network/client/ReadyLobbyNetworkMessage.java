package com.ss20.se2.monopoly.network.client;

import java.io.Serializable;

public class ReadyLobbyNetworkMessage extends NetworkMessage implements Serializable{
	private boolean value;

	public boolean isValue(){
		return value;
	}

	public void setValue(boolean value){
		this.value = value;
	}
}
