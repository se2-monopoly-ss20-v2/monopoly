package com.ss20.se2.monopoly.models;

import com.ss20.se2.monopoly.models.fields.cards.Card;
import com.ss20.se2.monopoly.models.fields.deeds.Deed;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable{

	private String name;
	private InetAddress address;
	private int port;
	private int balance;
	private GamePiece selectedPiece;
	private int currentPosition;
	private List<Deed> playersDeeds;
	private List<Card> playersCards;
	private boolean inJail;
	private boolean hasTurn;

	public Player(String name, int balance, GamePiece selectedPiece, int currentPosition, InetAddress address, int port){
		this.name = name;
		this.balance = balance;
		this.selectedPiece = selectedPiece;
		this.currentPosition = currentPosition;
		this.playersDeeds = new ArrayList<>();
		//this.playersCards = new ArrayList<>();
		this.inJail = false;
		this.address = address;
		this.port = port;
		this.hasTurn = false;
	}

	public String getName(){
		return name;
	}

	public int getBalance(){
		return balance;
	}

	public GamePiece getSelectedPiece(){
		return selectedPiece;
	}

	public int getCurrentPosition(){
		return currentPosition;
	}

	public void setBalance(int balance){
		this.balance = balance;
	}

	public void setCurrentPosition(int currentPosition){
		this.currentPosition = currentPosition;
	}

    public List<Deed> getPlayersDeeds(){
        return playersDeeds;
    }

    public void addDeedToPlayer(Deed deed){
        this.playersDeeds.add(deed);
    }

    public void removeDeedFromPlayer(Deed deed){
	    this.playersDeeds.remove(deed);
    }

    public List<Card> getPlayersCards(){
        return playersCards;
    }

    public void addCardToPlayer(Card card){
	    this.playersCards.add(card);
    }

    public void removeCardFromPlayer(Card card){
	    this.playersCards.remove(card);
    }

    public void updateBalance(int balance) { this.balance = balance;}

	public boolean isInJail(){
		return inJail;
	}

	public void setInJail(boolean inJail){
		this.inJail = inJail;
	}

	public InetAddress getAddress(){
		return address;
	}

	public void setAddress(InetAddress address){
		this.address = address;
	}

	public int getPort(){
		return port;
	}

	public void setPort(int port){
		this.port = port;
	}

	public boolean isHasTurn(){
		return hasTurn;
	}

	public void setHasTurn(boolean hasTurn){
		this.hasTurn = hasTurn;
	}
}
