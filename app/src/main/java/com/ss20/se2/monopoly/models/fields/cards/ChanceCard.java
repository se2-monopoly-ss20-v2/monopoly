package com.ss20.se2.monopoly.models.fields.cards;

public class ChanceCard extends Card{

	private String description;

	public ChanceCard(String name, String description){
		super(name);
		this.description = description;
	}
	public String getDescription(){
		return description;
	}
}
