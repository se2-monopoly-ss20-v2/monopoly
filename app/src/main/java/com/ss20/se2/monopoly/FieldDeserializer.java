package com.ss20.se2.monopoly;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.ss20.se2.monopoly.models.fields.GameTile;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class FieldDeserializer implements JsonDeserializer<GameTile>{

	private String fieldTypeElementName;
	private Gson gson;
	private Map<String, Class<? extends GameTile>> gameTileRegistry;

	public FieldDeserializer(String fieldTypeElementName){
		this.fieldTypeElementName = fieldTypeElementName;
		this.gson = new Gson();
		this.gameTileRegistry = new HashMap<>();
	}

	public void registerFieldType(String typeName, Class<? extends GameTile> type) {
		gameTileRegistry.put(typeName, type);
	}
	@Override
	public GameTile deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException{
		JsonObject gameTileObject = json.getAsJsonObject();
		JsonElement gameTileTypeElement = gameTileObject.get(fieldTypeElementName);

		Class<? extends GameTile> gameTileType = gameTileRegistry.get(gameTileTypeElement.getAsString());
		return gson.fromJson(gameTileObject, gameTileType);
	}
}
