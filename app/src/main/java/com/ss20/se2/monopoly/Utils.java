package com.ss20.se2.monopoly;

import android.content.Context;
import android.util.Log;

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

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Utils{

	public String getJSONFromAssets(Context context, String language){
		String inputString;
		try{
			InputStream is = context.getAssets().open(language + "-config.json");
			int size = is.available();
			byte[] buffer = new byte[size];
			if (size == is.read(buffer)){
				is.close();
				inputString = new String(buffer, StandardCharsets.UTF_8);
			}else{
				return null;
			}
		}catch (IOException e){
			Log.e("JSONLOADING", e.getMessage());
			return null;
		}
		return inputString;
	}

	public List<GameTile> getGameTilesRelativeFrom(String inputString){
		FieldDeserializer deserializer = new FieldDeserializer("type");
		deserializer.registerFieldType("Street", Street.class);
		deserializer.registerFieldType("Railroad", Railroad.class);
		deserializer.registerFieldType("Utility", Utility.class);
		deserializer.registerFieldType("ChanceCard", ChanceCard.class);
		deserializer.registerFieldType("CommunityCard", CommunityCard.class);
		deserializer.registerFieldType("Special", Special.class);

		Gson gson = new GsonBuilder().registerTypeAdapter(GameTile.class, deserializer).create();
		return gson.<ArrayList<GameTile>> fromJson(inputString, new TypeToken<List<GameTile>>(){}.getType());
	}
}
