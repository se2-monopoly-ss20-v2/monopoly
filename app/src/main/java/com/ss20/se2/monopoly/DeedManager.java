package com.ss20.se2.monopoly;

import com.ss20.se2.monopoly.models.Gameboard;
import com.ss20.se2.monopoly.models.Player;
import com.ss20.se2.monopoly.models.fields.deeds.Deed;
import com.ss20.se2.monopoly.models.fields.deeds.Street;

public class DeedManager{

	private Gameboard gameboard;
	public DeedManager(Gameboard gameboard){
		this.gameboard = gameboard;
	}

	public boolean playerOwnsAllStreetsOf(String color, Player player) {
		for (Street suitableStreet : gameboard.getStreetsRelativeTo(color)) {
			if (!suitableStreet.getOwner().equals(player)) {
				return false;
			}
		}
		return true;
	}

	public int performAcquiringDeed(Deed deed, Player player) {
		if (deed.getPrice() <= player.getBalance()) {
			//PLAYER CAN BUY IT
			int newBalance = player.getBalance() - deed.getPrice();
			player.updateBalance(newBalance);
			player.addDeedToPlayer(deed);

		}

		return player.getBalance();
	}

	public int performAcquiringHouseFor(Street street, Player player) {
		if (street.getHousePrice() <= player.getBalance() && street.getHouseCount() < 4) {
			int newBalance = player.getBalance() - street.getHousePrice();
			player.updateBalance(newBalance);
			street.incrementHouseCount();
		}

		return player.getBalance();
	}
}
