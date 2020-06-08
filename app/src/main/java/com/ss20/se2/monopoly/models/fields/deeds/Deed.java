package com.ss20.se2.monopoly.models.fields.deeds;

import com.ss20.se2.monopoly.models.Player;
import com.ss20.se2.monopoly.models.fields.GameTile;

/*
 * Deed definition:
 * A deed for each property is given to a player to signify ownership, and specifies purchase price, mortgage value,
 * the cost of building houses and hotels on that property, and the various rents depending on how developed the property is.
 *
 * When you have the physical version of monopoly this relates to all Cards a Player can buy.
 * Can also be understood as - (German): Sammelbegriff für Gründstücke, Bahnhöfe, Werke.
 */
public abstract class Deed extends GameTile {

	private int price;
	private int mortgage;
	private Player owner;
	private boolean isMortgaged;


	Deed(String name, int price, int mortgage, boolean isMortgaged){
		super(name);
		this.price = price;
		this.mortgage = mortgage;
		this.isMortgaged = isMortgaged;
	}

	public int getPrice(){
		return price;
	}

	public int getMortgage(){
		return mortgage;
	}

	public Player getOwner(){
		return owner;
	}

	public void setOwner(Player owner){
		this.owner = owner;
	}

	public boolean getIsMortgaged(){
		return isMortgaged;
	}

	public void setIsMortgaged(boolean isMortgaged){
		this.isMortgaged = isMortgaged;
	}

	@Override
	public String toString(){
		return this.getName();
	}
}
