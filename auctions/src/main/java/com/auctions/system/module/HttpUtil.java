package com.auctions.system.module;

import java.io.IOException;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonObject;
import com.liferay.portal.kernel.util.PortalUtil;

public class HttpUtil {
	
	public static void createResponse(ResourceResponse response, List<ResponseParam> params) throws IOException{
		JsonObject obj = new JsonObject();
		for(ResponseParam param : params){
			obj.addProperty(param.getName(), param.getValue());
		}
		response.setContentType("application/json");
		response.getWriter().write(obj.toString());
	}
	
	public static HttpServletRequest getOriginal(PortletRequest request){
		return PortalUtil.getOriginalServletRequest(
				PortalUtil.getHttpServletRequest(request));
	}

}
