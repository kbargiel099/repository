package com.auctions.system.module;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class Serializer {

	private static Gson gson = new Gson();
	
	public static <T> String toJson(T value){
		return gson.toJson(value);
	}
	
	public static <T> T fromJson(String value, Class<T> className){
		return gson.fromJson(value, (Class<T>) className);
	}
	
	public static <T> JsonElement toJsonTree(List<T> value){
		return gson.toJsonTree(value);
	}
	
}
