package com.auctions.system.module.message_category.model;

public class MessageCategory {
	
	private int id;
	private String name;
	private String communicationChannel;
	
	public MessageCategory(int id, String name, String communicationChannel) {
		super();
		this.id = id;
		this.name = name;
		this.communicationChannel = communicationChannel;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCommunicationChannel() {
		return communicationChannel;
	}
	public void setCommunicationChannel(String communicationChannel) {
		this.communicationChannel = communicationChannel;
	}
	
}
