package com.auctions.system.portlet.auctions_management.interceptor;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.handler.HandlerInterceptorAdapter;

import com.auctions.system.module.Properties;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.UserServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;

public class AuctionManagementInterceptor extends HandlerInterceptorAdapter {

	private final String errorView = "error";
	
    @Override   
    public void postHandleRender(RenderRequest request, RenderResponse response, Object portletController, ModelAndView modelAndView) throws Exception {        
        long userId = PortalUtil.getUserId(request);
        
        try {
	        if (!UserServiceUtil.hasRoleUser(Properties.getAdministratorRoleid(request), userId)) {
	        	modelAndView.setView(errorView);
	        	modelAndView.addObject("errorKey", "permission.denied");
	        }
        } catch (PrincipalException e) {
        	modelAndView.setView(errorView);
        	modelAndView.addObject("errorKey", "permission.denied");
        }
    }

}
