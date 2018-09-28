package com.auctions.system.module;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DateFormatter {

	private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
	
	public static String format(Timestamp e){
		return sdf.format(e);
	}
	
	public static String formatForView(Timestamp e){
		return new SimpleDateFormat("yyyy-MM-dd").format(e);
	}
}
