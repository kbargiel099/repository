package com.auctions.system.portlet.user_profile.model;

public class Image {

	private String name;
	private String data;
	
	public Image(String name, String data){
		this.name = name;
		this.data = data;
	};
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setData(String data){
		this.data = data;
	}
	
	public String getName(){
		return name;
	}
	
	public String getData(){
		return data;
	}
}
