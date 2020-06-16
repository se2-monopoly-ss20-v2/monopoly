package com.ss20.se2.monopoly.models;

/**
 * This interface should be implemented in the user interface classes where required. It can
 * then be passed to the @see {@link com.ss20.se2.monopoly.network.client.GameController} to be
 * notified in case of changes. After implementation, only functionality has to be added to the
 * required methods.
 */
public interface OnGameDataChangedListener{

	void onGameJoined(Player player);
	// ...
}
