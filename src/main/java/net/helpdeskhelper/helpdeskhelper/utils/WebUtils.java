package net.helpdeskhelper.helpdeskhelper.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
// Spring
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

// org.glassfish.jersey
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

public class WebUtils {

	@Value("${geoLoc.APIKey}")
	private static String geoLocAPIKey;
	
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
	
	// Checks for proxy forwarded IP adress, if none, gets actual IP adress.
	public static String getIPFromServletRequest(HttpServletRequest req) {
		
		String ipAddress = req.getHeader("x-forwarded-for");
        if (ipAddress == null) {
            ipAddress = req.getHeader("X_FORWARDED_FOR");
            if (ipAddress == null)
                ipAddress = req.getRemoteAddr();            
        }    
        return ipAddress;
	}
	
	// Gets domain name from servlet request.
	public static String getDomainFromServletRequest(HttpServletRequest req) {
		
		String domain = req.getRemoteHost();		
		if(domain == null)
			return "Domain not found!";			
		return domain;
	}
	
	// Queries geolocation API on IP - Service documentation: https://ipdata.co/docs.html
	public static void getGeolocationInfoIPv4(String ip) throws IOException{
		
		System.out.println(geoLocAPIKey);
		String geoURL = "https://api.ipdata.co/" +ip +"?api-key=" +geoLocAPIKey;
		Client client = ClientBuilder.newClient();
		Response response = client.target(geoURL)
		  .request(MediaType.TEXT_PLAIN_TYPE)
		  .header("Accept", "application/json")
		  .get();

		System.out.println("body:" + response.readEntity(String.class));
	}
}
