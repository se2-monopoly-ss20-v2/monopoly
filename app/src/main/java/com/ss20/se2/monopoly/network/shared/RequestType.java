package com.ss20.se2.monopoly.network.shared;

import java.util.Locale;

public enum RequestType{
	JOIN_GAME, LEAVE_GAME, CHANGE_GAME_PIECE, ROLL_DICE, SKIP_TURN, BUY_DEED, SELL_DEED, BUY_HOUSE, SELL_HOUSE, BUY_HOTEL, SELL_HOTEL, RAISE_MORTGAGE, REDEEM_MORTGAGE, TRADE_DEED, BID_AT_AUCTION, CHEAT;

	public static String toString(RequestType type){
		return type.name().toLowerCase(Locale.US).replace("_", "-");
	}

	public static RequestType toEnum(String type){
		return RequestType.valueOf(type.toUpperCase(Locale.US).replace("-", "_"));
	}
}
