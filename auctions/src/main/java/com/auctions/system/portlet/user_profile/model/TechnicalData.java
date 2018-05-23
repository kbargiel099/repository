package com.auctions.system.portlet.user_profile.model;

import java.sql.Array;
import java.sql.SQLException;

public class TechnicalData {

	private int id;
	private String name;
	private String lang;
	private String type;
	private String[] value;
	private String[] valueLang;
	
	public TechnicalData(int id, String name, String type, String[] value) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.value = value;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public TechnicalData(int id, String name, String type, Array array) {
		this.id = id;
		this.name = name;
		this.type = type;
		try {
			this.value = (String[]) array.getArray();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String[] getValue() {
		return value;
	}
	
	public void setValue(String[] value) {
		this.value = value;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String[] getValueLang() {
		return valueLang;
	}

	public void setValueLang(String[] valueLang) {
		this.valueLang = valueLang;
	}
	
}
