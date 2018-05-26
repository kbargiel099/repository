package com.auctions.system.module;

import java.io.IOException;
import java.util.List;

import javax.portlet.ResourceResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ResponseWrapper {

	private Gson gson = new Gson();
	private JsonObject obj = new JsonObject();
	private ResourceResponse response;
	
	public ResponseWrapper(ResourceResponse response){
		this.response = response;	
	}
	
	public <T> ResponseWrapper set(String name, T value){
		obj.addProperty(name, gson.toJson(value));
		//obj.addProperty(name, String.valueOf(value));
		return this;
	}
	
	public <T> ResponseWrapper set(String name, List<T> value){
		obj.add(name, gson.toJsonTree(value));
		return this;
	}
	
	public void prepare() throws IOException{
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(obj.toString());
	}
}
