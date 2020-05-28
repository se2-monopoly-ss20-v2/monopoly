package com.ss20.se2.monopoly.network.gamestate;

import com.ss20.se2.monopoly.models.GameState;
import com.ss20.se2.monopoly.network.client.NetworkMessage;

import java.io.Serializable;

public class GameStateNetworkMessage extends NetworkMessage implements Serializable{
	GameState state;

	public GameState getState(){
		return state;
	}

	public void setState(GameState state){
		this.state = state;
	}
}
