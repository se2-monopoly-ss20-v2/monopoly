package com.ss20.se2.monopoly.network.shared;

import com.ss20.se2.monopoly.network.GameStateResponse;
import com.ss20.se2.monopoly.network.server.LobbyResponse;

/**
 * This interface defines all possible user interactions. It is implemented on the client side to
 * build the request. On the server side, it is implemented to call the appropriate method for
 * calculating after parsing the request, since the parameters and usage are the same.
 * Can be extended if any new interactions have to be implemented.
 */
public interface GameResponses{

	void lobby(LobbyResponse response);

	void state(GameStateResponse response);
}
