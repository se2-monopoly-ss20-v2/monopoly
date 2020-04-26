package com.ss20.se2.monopoly.network.server;

public class GameServerNotRunningException extends Exception{

	public GameServerNotRunningException(String errorMessage){
		super(errorMessage);
	}
}
