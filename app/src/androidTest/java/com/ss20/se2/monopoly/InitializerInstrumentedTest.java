package com.ss20.se2.monopoly;

import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;

import com.esotericsoftware.kryo.util.Util;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.ss20.se2.monopoly.models.fields.GameTile;
import com.ss20.se2.monopoly.models.fields.cards.ChanceCard;
import com.ss20.se2.monopoly.models.fields.cards.CommunityCard;
import com.ss20.se2.monopoly.models.fields.deeds.Railroad;
import com.ss20.se2.monopoly.models.fields.deeds.Street;
import com.ss20.se2.monopoly.models.fields.deeds.Utility;
import com.ss20.se2.monopoly.models.fields.specials.Special;
import com.ss20.se2.monopoly.models.fields.specials.SpecialFieldType;

import org.junit.Test;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InitializerInstrumentedTest{

	@Test
	public void testATFields(){
		Utils utils = new Utils();
		String inputString = utils.getJSONFromAssets(InstrumentationRegistry.getInstrumentation().getTargetContext(), "at");
		ArrayList<GameTile> tiles = (ArrayList<GameTile>) utils.getGameTilesRelativeFrom(inputString);
		assertEquals(4, tiles.size());
		assertTrue(tiles.get(0) instanceof Street);
		assertTrue(tiles.get(1) instanceof Street);
		assertTrue(tiles.get(2) instanceof Railroad);
		assertTrue(tiles.get(3) instanceof Utility);
	}

	@Test
	public void testENFields(){
		Utils utils = new Utils();
		String inputString = utils.getJSONFromAssets(InstrumentationRegistry.getInstrumentation().getTargetContext(), "en");
		ArrayList<GameTile> tiles = (ArrayList<GameTile>) utils.getGameTilesRelativeFrom(inputString);
		assertEquals(27, tiles.size());
		assertTrue(tiles.get(0) instanceof Special);
		Special sp = (Special) tiles.get(0);
		assertEquals(SpecialFieldType.GO, ((Special) tiles.get(0)).getFieldType());
		assertTrue(tiles.get(1) instanceof Street);
		assertTrue(tiles.get(2) instanceof Street);
		assertTrue(tiles.get(3) instanceof Railroad);
		assertTrue(tiles.get(4) instanceof Street);
		assertTrue(tiles.get(5) instanceof Street);
		assertTrue(tiles.get(6) instanceof Street);
		assertTrue(tiles.get(7) instanceof Street);
		assertTrue(tiles.get(8) instanceof Street);
		assertTrue(tiles.get(9) instanceof Street);
		assertTrue(tiles.get(10) instanceof Railroad);
		assertTrue(tiles.get(11) instanceof Street);
		assertTrue(tiles.get(12) instanceof Street);
		assertTrue(tiles.get(13) instanceof Street);
		assertTrue(tiles.get(14) instanceof Street);
		assertTrue(tiles.get(15) instanceof Street);
		assertTrue(tiles.get(16) instanceof Street);
		assertTrue(tiles.get(17) instanceof Railroad);
		assertTrue(tiles.get(18) instanceof Street);
		assertTrue(tiles.get(19) instanceof Street);
		assertTrue(tiles.get(20) instanceof Street);
		assertTrue(tiles.get(21) instanceof Street);
		assertTrue(tiles.get(22) instanceof Street);
		assertTrue(tiles.get(23) instanceof Street);
		assertTrue(tiles.get(24) instanceof Railroad);
		assertTrue(tiles.get(25) instanceof Street);
		assertTrue(tiles.get(26) instanceof Street);
	}
}
