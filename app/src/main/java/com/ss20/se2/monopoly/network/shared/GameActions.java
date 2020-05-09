package com.ss20.se2.monopoly.network.shared;

import com.ss20.se2.monopoly.models.GamePiece;
import com.ss20.se2.monopoly.models.Player;
import com.ss20.se2.monopoly.models.fields.deeds.Deed;
import com.ss20.se2.monopoly.network.client.ChangeGamePieceNetworkMessage;
import com.ss20.se2.monopoly.network.client.JoinLobbyNetworkMessage;
import com.ss20.se2.monopoly.network.client.LeaveLobbyNetworkMessage;
import com.ss20.se2.monopoly.network.client.ReadyLobbyNetworkMessage;

/**
 * This interface defines all possible user interactions. It is implemented on the client side to
 * build the request. On the server side, it is implemented to call the appropriate method for
 * calculating after parsing the request, since the parameters and usage are the same.
 * Can be extended if any new interactions have to be implemented.
 */
public interface GameActions{

	void joinLobby(JoinLobbyNetworkMessage message);

	void leaveLobby(LeaveLobbyNetworkMessage message);

	void changeGamePiece(ChangeGamePieceNetworkMessage message);

	void changeReadyLobby(ReadyLobbyNetworkMessage message);

	void rollDice();

	void skipTurn();

	void buyDeed(Deed deed);

	void sellDeed(Deed deed);

	void buyHouse(Deed deed);

	void sellHouse(Deed deed);

	void buyHotel(Deed deed);

	void sellHotel(Deed deed);

	void raiseMortgage(Deed deed);

	void redeemMortgage(Deed deed);

	void tradeDeed(Deed deed, Player player);

	void bidAtAuction(int amount);

	void cheat();
}
