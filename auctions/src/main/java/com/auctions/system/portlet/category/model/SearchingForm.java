package com.auctions.system.portlet.category.model;

public class SearchingForm {
	
	private String searchingText;
	private String currentCategory;
	private String minPrice = "0";
	private String maxPrice = "0";
	
	public SearchingForm(String searchingText,String currentCategory, String minPrice, String maxPrice) {
		super();
		this.searchingText = searchingText;
		this.currentCategory =currentCategory;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}
	
	public String getSearchingText() {
		return searchingText;
	}
	public void setSearchingText(String searchingText) {
		this.searchingText = searchingText;
	}
	public String getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(String minPrice) {
			this.minPrice = minPrice;
	}
	public String getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(String maxPrice) {
			this.maxPrice = maxPrice;
	}

	public String getCurrentCategory() {
		return currentCategory;
	}

	public void setCurrentCategory(String currentCategory) {
		this.currentCategory = currentCategory;
	}
	
}
