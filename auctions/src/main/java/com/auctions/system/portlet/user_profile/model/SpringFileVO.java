package com.auctions.system.portlet.user_profile.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;
 
public class SpringFileVO {
 
    private CommonsMultipartFile fileData;
    private long auctionId;
    private String message;
 
    public long getAuctionId() {
		return auctionId;
	}

	public void setAuctionId(long auctionId) {
		this.auctionId = auctionId;
	}

	public String getMessage() {
        return message;
    }
 
    public void setMessage(String message) {
        this.message = message;
    }
 
    public CommonsMultipartFile getFileData() {
        return fileData;
    }
 
    public void setFileData(CommonsMultipartFile fileData) {
        this.fileData = fileData;
    }
     
}
