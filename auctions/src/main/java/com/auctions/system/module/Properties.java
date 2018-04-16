package com.auctions.system.module;

public class Properties {
	
	private final static String path = System.getProperty("catalina.base") + "\\webapps\\";
	
	private final static String imagesPath = path + "images\\";
	
	private final static String videosPath = path + "videos\\";
	
	public static String getImagesPath(){
		return imagesPath;
	}
	
	public static String getVideosPath(){
		return videosPath;
	}

}
