package com.auctions.system.portlet.user_profile.model;

import java.sql.Array;
import java.sql.SQLException;

public class AuctionImages {

	private long id;
	private String[] images;
	
	public AuctionImages(long id, Array images) throws SQLException{
		this.id = id;
		this.images = (String[]) images.getArray();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String[] getImages() {
		return images;
	}

	public void setImages(String[] images) {
		this.images = images;
	}
	
}
