package com.ss20.se2.monopoly.models.fields.specials;

import com.ss20.se2.monopoly.models.fields.GameTile;

public class Special extends GameTile{

	private SpecialFieldType fieldType;

	public Special(String name, SpecialFieldType fieldType){
		super(name);
		this.fieldType = fieldType;
	}

	public SpecialFieldType getFieldType(){
		return fieldType;
	}
}
