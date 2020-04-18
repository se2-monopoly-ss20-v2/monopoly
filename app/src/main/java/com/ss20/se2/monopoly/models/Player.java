package com.ss20.se2.monopoly.models;

import com.ss20.se2.monopoly.models.deeds.Card;
import com.ss20.se2.monopoly.models.deeds.Deed;

import java.util.ArrayList;
import java.util.List;

public class Player{

	private String name;
	private int balance;
	private GamePiece selectedPiece;
	private int currentPosition;
	private List<Deed> playersDeeds;
	private List<Card> playersCards;

	public Player(String name, int balance, GamePiece selectedPiece, int currentPosition){
		this.name = name;
		this.balance = balance;
		this.selectedPiece = selectedPiece;
		this.currentPosition = currentPosition;
		this.playersDeeds = new ArrayList<>();
		this.playersCards = new ArrayList<>();
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
}
