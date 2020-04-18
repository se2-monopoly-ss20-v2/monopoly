package com.ss20.se2.monopoly.models.fields;

public abstract class Card extends GameTile{

	private String description;

	Card(String name, String description){
		super(name);
		this.description = description;
	}

	public String getDescription(){
		return description;
	}
}
