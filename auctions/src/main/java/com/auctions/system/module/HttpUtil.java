package com.auctions.system.module;

import javax.portlet.PortletRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.kernel.util.PortalUtil;

public class HttpUtil {
	
	public static ResponseWrapper createResponse(ResourceResponse response){
		return new ResponseWrapper(response);
	}
	
	public static HttpServletRequest getOriginal(PortletRequest request){
		return PortalUtil.getOriginalServletRequest(
				PortalUtil.getHttpServletRequest(request));
	}

}
