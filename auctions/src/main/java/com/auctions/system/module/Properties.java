package com.auctions.system.module;

import javax.portlet.RenderRequest;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;

public class Properties {
	
	private final static String rootPath = System.getProperty("catalina.base") + "\\webapps\\";
	
	private final static String imagesPath = rootPath + "images\\";
	
	private final static String videosPath = rootPath + "videos\\";
	
	private final static String restServiceEndpoint = "http://192.168.0.15:8143/notification";
	
	private final static String administratorRoleName = "Administrator";
	
	private final static String userRoleName = "User";
	
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

	public static long getAdministratorRoleid(RenderRequest request) {
		return getRoleByName(request, administratorRoleName);
	}

	public static long getUserRoleid(RenderRequest request) {
		return getRoleByName(request, userRoleName);
	}
	
	private static long getRoleByName(RenderRequest request, String roleName) {
		 long companyId = PortalUtil.getCompanyId(request);
		 
		 try {
			return RoleLocalServiceUtil.getRole(companyId, roleName).getRoleId();
		} catch (PortalException e) {
			e.printStackTrace();
		}
		 
		return 0;
	}
}
