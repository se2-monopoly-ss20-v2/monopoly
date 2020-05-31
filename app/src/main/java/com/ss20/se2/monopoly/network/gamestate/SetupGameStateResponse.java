package com.ss20.se2.monopoly.network.gamestate;

import com.ss20.se2.monopoly.models.GameState;
import com.ss20.se2.monopoly.network.server.NetworkResponse;

import java.io.Serializable;

public class SetupGameStateResponse extends NetworkResponse implements Serializable{
	GameState state;

	public GameState getState(){
		return state;
	}

	public void setState(GameState state){
		this.state = state;
	}
}
