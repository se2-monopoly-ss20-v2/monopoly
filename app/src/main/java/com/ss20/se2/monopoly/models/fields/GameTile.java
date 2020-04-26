package com.ss20.se2.monopoly.models.fields;

public abstract class GameTile{
	public static final String TYPE = "GameTile";
	private String name;

	public GameTile(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}
}
