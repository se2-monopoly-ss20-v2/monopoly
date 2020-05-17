package com.ss20.se2.monopoly.network.client;

import com.ss20.se2.monopoly.models.GamePiece;

import java.io.Serializable;

public class JoinLobbyNetworkMessage extends NetworkMessage  implements Serializable{
	GamePiece gamePiece;

	public GamePiece getGamePiece(){
		return gamePiece;
	}

	public void setGamePiece(GamePiece gamePiece){
		this.gamePiece = gamePiece;
	}
}
