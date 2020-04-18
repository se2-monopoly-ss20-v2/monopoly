package com.ss20.se2.monopoly.pojo.models.deeds;

public abstract class GameTile{

	private String name;

	GameTile(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}
}
