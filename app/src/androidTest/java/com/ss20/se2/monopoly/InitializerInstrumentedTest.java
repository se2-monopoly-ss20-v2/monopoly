package com.ss20.se2.monopoly;

import androidx.test.platform.app.InstrumentationRegistry;

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
		assertEquals(40, tiles.size());

		assertTrue(tiles.get(0) instanceof Special);
		assertEquals(SpecialFieldType.GO, ((Special) tiles.get(0)).getFieldType());
		assertTrue(tiles.get(1) instanceof Street);
		assertTrue(tiles.get(2) instanceof CommunityCard);
		assertTrue(tiles.get(3) instanceof Street);
		assertTrue(tiles.get(4) instanceof Special);
		assertEquals(SpecialFieldType.INCOME_TAX, ((Special) tiles.get(4)).getFieldType());
		assertTrue(tiles.get(5) instanceof Railroad);
		assertTrue(tiles.get(6) instanceof Street);
		assertTrue(tiles.get(7) instanceof ChanceCard);
		assertTrue(tiles.get(8) instanceof Street);
		assertTrue(tiles.get(9) instanceof Street);
		assertTrue(tiles.get(10) instanceof Special);
		assertEquals(SpecialFieldType.JAIL_VISITOR ,((Special)tiles.get(10)).getFieldType());
		assertTrue(tiles.get(11) instanceof Street);
		assertTrue(tiles.get(12) instanceof Utility);
		assertTrue(tiles.get(13) instanceof Street);
		assertTrue(tiles.get(14) instanceof Street);
		assertTrue(tiles.get(15) instanceof Railroad);
		assertTrue(tiles.get(16) instanceof Street);
		assertTrue(tiles.get(17) instanceof CommunityCard);
		assertTrue(tiles.get(18) instanceof Street);
		assertTrue(tiles.get(19) instanceof Street);
		assertTrue(tiles.get(20) instanceof Special);
		assertEquals(SpecialFieldType.FREEPARKING ,((Special)tiles.get(20)).getFieldType());
		assertTrue(tiles.get(21) instanceof Street);
		assertTrue(tiles.get(22) instanceof ChanceCard);
		assertTrue(tiles.get(23) instanceof Street);
		assertTrue(tiles.get(24) instanceof Street);
		assertTrue(tiles.get(25) instanceof Railroad);
		assertTrue(tiles.get(26) instanceof Street);
		assertTrue(tiles.get(27) instanceof Street);
		assertTrue(tiles.get(28) instanceof Utility);
		assertTrue(tiles.get(29) instanceof Street);
		assertTrue(tiles.get(30) instanceof Special);
		assertEquals(SpecialFieldType.JAIL ,((Special)tiles.get(30)).getFieldType());
		assertTrue(tiles.get(31) instanceof Street);
		assertTrue(tiles.get(32) instanceof Street);
		assertTrue(tiles.get(33) instanceof CommunityCard);
		assertTrue(tiles.get(34) instanceof Street);
		assertTrue(tiles.get(35) instanceof Railroad);
		assertTrue(tiles.get(36) instanceof ChanceCard);
		assertTrue(tiles.get(37) instanceof Street);
		assertTrue(tiles.get(38) instanceof Special);
		assertEquals(SpecialFieldType.LUXURY_TAX ,((Special)tiles.get(38)).getFieldType());
		assertTrue(tiles.get(39) instanceof Street);

	}
}
