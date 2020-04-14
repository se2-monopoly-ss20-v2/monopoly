package com.ss20.se2.monopoly.network;

import com.ss20.se2.monopoly.pojo.LocalGame;

import java.util.List;

public interface OnLocalGamesChangedListener{

	void onGamesChanged(List<LocalGame> foundGames);
}
