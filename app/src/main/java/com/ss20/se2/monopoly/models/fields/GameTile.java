package com.ss20.se2.monopoly.models.fields;

import java.io.Serializable;

public abstract class GameTile implements Serializable{
	public static final String TYPE = "GameTile";
	private String name;

	public GameTile(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}
}
