package com.ss20.se2.monopoly.models.fields.specials;

import com.ss20.se2.monopoly.models.fields.GameTile;

public class Special extends GameTile{

	private SpecialFieldType type;

	public Special(String name, SpecialFieldType type){
		super(name);
		this.type = type;
	}
}
