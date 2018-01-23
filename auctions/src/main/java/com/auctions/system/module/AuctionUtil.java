package com.auctions.system.module;

public class AuctionUtil {

	private final static String initialStatusOfCreatedAuction = "active";
	
	public static String createSerialNumber(){
		return "1234567890";
	}
	
	public static String getInitialStatusOfCreatedAuction(){
		return initialStatusOfCreatedAuction;
	}
}
