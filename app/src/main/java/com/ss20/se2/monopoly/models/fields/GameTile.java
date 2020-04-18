package com.ss20.se2.monopoly.models.fields;

public abstract class GameTile{

	private String name;

	public GameTile(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}
}
