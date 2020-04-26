package com.ss20.se2.monopoly.network.shared;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RequestTypeTest{

	@Test
	public void requestTypeToStringTest(){
		String actual = RequestType.toString(RequestType.TRADE_DEED);
		String expected = RequestType.TRADE_DEED.name().toLowerCase().replace("_", "-");
		assertEquals(expected, actual);
	}

	@Test
	public void stringToRequestTypeTest(){
		RequestType actual = RequestType.toEnum("trade-deed");
		assertEquals(RequestType.TRADE_DEED, actual);
	}
}