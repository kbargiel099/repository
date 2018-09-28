package com.auctions.system.module.file_converter.dao;

public interface ConverterDAO {
	
	public boolean createVideoReference(long auctionId, String videoName);
}
