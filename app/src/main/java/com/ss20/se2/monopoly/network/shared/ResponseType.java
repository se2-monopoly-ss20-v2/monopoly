package com.ss20.se2.monopoly.network.shared;

import java.util.Locale;

public enum ResponseType{
	GAME_JOINED, GAME_LEFT, GAME_PIECE_CHANGED, GAME_PIECE_NOT_CHANGED, DICE_ROLLED, DICE_NOT_ROLLED, TURN_SKIPPED, TURN_NOT_SKIPPED, DEED_BOUGHT, DEED_NOT_BOUGHT, DEED_SOLD, DEED_NOT_SOLD, HOUSE_BOUGHT, HOUSE_NOT_BOUGHT, HOUSE_SOLD, HOUSE_NOT_SOLD, HOTEL_BOUGHT, HOTEL_NOT_BOUGHT, HOTEL_SOLD, HOTEL_NOT_SOLD, MORTGAGE_RAISED, MORTGAGE_NOT_RAISED, MORTGAGE_REDEEMED, MORTGAGE_NOT_REDEEMED, DEED_TRADED, DEED_NOT_TRADED, BIDDEN_ON_AUCTION, NOT_BIDDEN_ON_AUCTION, CHEATED, NOT_CHEATED;

	public static String toString(ResponseType type){
		return type.name().toLowerCase(Locale.US).replace("_", "-");
	}

	public static ResponseType toEnum(String type){
		return ResponseType.valueOf(type.toUpperCase(Locale.US).replace("-", "_"));
	}
}
