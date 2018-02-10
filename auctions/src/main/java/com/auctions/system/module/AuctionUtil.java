package com.auctions.system.module;

import java.util.Observable;

public class AuctionUtil {
	
	Observable t = new Observable();

	private final static String initialStatusOfCreatedAuction = "active";
	
	public static String createSerialNumber(){
		return "1234567890";
	}
	
	public static String getInitialStatusOfCreatedAuction(){
		return initialStatusOfCreatedAuction;
	}
	
}
