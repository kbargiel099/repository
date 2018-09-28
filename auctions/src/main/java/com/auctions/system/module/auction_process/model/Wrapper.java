package com.auctions.system.module.auction_process.model;

import java.util.List;

public class Wrapper {

	private List<TechnicalParameter> data;

	public Wrapper(List<TechnicalParameter> data) {
		super();
		this.data = data;
	}

	public List<TechnicalParameter> getData() {
		return data;
	}

	public void setData(List<TechnicalParameter> data) {
		this.data = data;
	}
	
}
