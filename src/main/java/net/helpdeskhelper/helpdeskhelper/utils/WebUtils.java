package net.helpdeskhelper.helpdeskhelper.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class WebUtils {

	
	public static List<String> userInfoToList(User userParam) {
		
		List<String> userInfo = new ArrayList<String>();
		userInfo.add(userParam.getUsername());
		
		Collection<GrantedAuthority> authoritiesGranted = userParam.getAuthorities();
		
		if(authoritiesGranted != null && !authoritiesGranted.isEmpty()) {
			for(GrantedAuthority a : authoritiesGranted) {
				userInfo.add(a.getAuthority());
			}
		}
		
		return userInfo;
	}
}
