package com.ss20.se2.monopoly.models.deeds;
/*
 * Deed definition:
 * A deed for each property is given to a player to signify ownership, and specifies purchase price, mortgage value,
 * the cost of building houses and hotels on that property, and the various rents depending on how developed the property is.
 *
 * When you have the physical version of monopoly this relates to all Cards a Player can buy.
 * Can also be understood as - (German): Sammelbegriff für Gründstücke, Bahnhöfe, Werke.
 */
public abstract class Deed extends GameTile{

	private int price;
	private int mortgage;

	Deed(String name, int price, int mortgage){
		super(name);
		this.price = price;
		this.mortgage = mortgage;
	}

	public int getPrice(){
		return price;
	}

	public int getMortgage(){
		return mortgage;
	}
}
