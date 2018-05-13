package com.auctions.system.module.auction_processing.model;

public class PaymentMethod {

	private int id;
	private String name;
	private int validityInDays;
	
	public PaymentMethod(int id, String name, int validityInDays) {
		super();
		this.id = id;
		this.name = name;
		this.validityInDays = validityInDays;
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
	
	public int getValidityInDays() {
		return validityInDays;
	}
	
	public void setValidityInDays(int validityInDays) {
		this.validityInDays = validityInDays;
	}
	
}
