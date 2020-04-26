package com.ss20.se2.monopoly.network.server;

public class GameServerAlreadyRunningException extends Exception{

	public GameServerAlreadyRunningException(String errorMessage){
		super(errorMessage);
	}
}
