package com.auctions.system.module;

public class Properties {
	
	private final static String rootPath = System.getProperty("catalina.base") + "\\webapps\\";
	
	private final static String imagesPath = rootPath + "images\\";
	
	private final static String videosPath = rootPath + "videos\\";
	
	private final static String restServiceEndpoint = "http://192.168.0.15:8143/notification";
	
	private final static long administratorRoleId = 20121;
	
	private final static long userRoleId = 20125;
	
	public static String getRestServiceEndpoint() {
		return restServiceEndpoint;
	}

	public static String getImagesPath(){
		return imagesPath;
	}
	
	public static String getVideosPath(){
		return videosPath;
	}

	public static String getRestserviceendpoint() {
		return restServiceEndpoint;
	}

	public static long getAdministratorRoleid() {
		return administratorRoleId;
	}

	public static long getUserRoleid() {
		return userRoleId;
	}
}
