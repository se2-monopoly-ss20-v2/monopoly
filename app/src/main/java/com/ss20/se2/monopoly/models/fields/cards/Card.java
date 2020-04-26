package com.ss20.se2.monopoly.models.fields.cards;

import com.ss20.se2.monopoly.models.fields.GameTile;

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
