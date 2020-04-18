package com.ss20.se2.monopoly.network;

import com.ss20.se2.monopoly.pojo.LocallyFoundGame;

import java.util.List;

public interface OnLocalGamesChangedListener{

	void onGamesChanged(List<LocallyFoundGame> foundGames);
}
