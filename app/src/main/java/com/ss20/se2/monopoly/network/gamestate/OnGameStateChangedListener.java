package com.ss20.se2.monopoly.network.gamestate;

import com.ss20.se2.monopoly.models.GameState;

public interface OnGameStateChangedListener{
	void onGameStateChanged(GameState gameState);
	void setupGameState(GameState gameState);
}
