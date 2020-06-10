package com.ss20.se2.monopoly.models;

import java.io.Serializable;

public class GamePiece implements Serializable{

	private String name;

	public GamePiece(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}
}
