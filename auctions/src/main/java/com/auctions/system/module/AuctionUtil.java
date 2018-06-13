package com.auctions.system.module;

public class AuctionUtil {

	private final static String initialStatusOfCreatedAuction = "active";
	
	public static String createSerialNumber(){
		return String.valueOf(System.currentTimeMillis());
	}
	
	public static String getInitialStatusOfCreatedAuction(){
		return initialStatusOfCreatedAuction;
	}
	
}
