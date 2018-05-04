package com.auctions.system.module;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

public class UserUtil {

	public static String getScreenName(long userId){
		String screenName = "";
		try {
			screenName =  UserLocalServiceUtil.getUserById(userId).getScreenName();

		} catch (PortalException e) {
			e.printStackTrace();
		}
		return screenName;
	}
}
