package com.auctions.system.module;

import java.io.Serializable;

public class Option implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String type;
	String url;
	
	public Option(String type, String url) {
		super();
		this.type = type;
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
