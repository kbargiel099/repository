package com.auctions.system.module;

public class ResponseParam {

	private String name;
	private String value;
	
	public ResponseParam(String name, Object value) {
		super();
		this.name = name;
		this.value = String.valueOf(value);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
