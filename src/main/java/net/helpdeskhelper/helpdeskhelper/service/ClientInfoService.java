package net.helpdeskhelper.helpdeskhelper.service;

//## Java
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//## Project
import net.helpdeskhelper.helpdeskhelper.utils.WebUtils;

//## Spring
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;



@Service
public class ClientInfoService implements IClientInfoService{
		
	@Value("${geoLoc.APIKey}")
	private String geoLocAPIKey;

	public String getClientInfo(HttpServletRequest request) throws IOException{
		
		//String ip = getClientIP(request);
		String geoLocInfo = getClientGeolocationInfoIPv4("84.212.9.174");

		System.out.println(request.getHeader("User-Agent"));
		geoLocInfo.concat("   USER AGENT: " +request.getHeader("User-Agent"));
		
		return geoLocInfo;
	}
			
	// Checks for proxy forwarded IP adress, if none, gets actual IP address.
	public String getClientIP(HttpServletRequest request) {
		
		String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null) {
            ipAddress = request.getHeader("X_FORWARDED_FOR");
            if (ipAddress == null)
                ipAddress = request.getRemoteAddr();            
        }    
        return ipAddress;
	}	
	
	public String getClientBrowser(HttpServletRequest request) {
		
	     final String browserDetails = request.getHeader("User-Agent");

	        //=================OS=======================
	        final String lowerCaseBrowser = browserDetails.toLowerCase();
	        if (lowerCaseBrowser.contains("windows")) {
	            return "Windows";
	        } else if (lowerCaseBrowser.contains("mac")) {
	            return "Mac";
	        } else if (lowerCaseBrowser.contains("x11")) {
	            return "Unix";
	        } else if (lowerCaseBrowser.contains("android")) {
	            return "Android";
	        } else if (lowerCaseBrowser.contains("iphone")) {
	            return "IPhone";
	        } else {
	            return "UnKnown, More-Info: " + browserDetails;
	        }
	}
	
	public String getClientOS(HttpServletRequest request) {
		
		final String browserDetails = request.getHeader("User-Agent");

        //=================OS=======================
        final String lowerCaseBrowser = browserDetails.toLowerCase();
        if (lowerCaseBrowser.contains("windows")) {
            return "Windows";
        } else if (lowerCaseBrowser.contains("mac")) {
            return "Mac";
        } else if (lowerCaseBrowser.contains("x11")) {
            return "Unix";
        } else if (lowerCaseBrowser.contains("android")) {
            return "Android";
        } else if (lowerCaseBrowser.contains("iphone")) {
            return "IPhone";
        } else {
            return "UnKnown, More-Info: " + browserDetails;
}
	}
	
	// Gets domain name from servlet request.
	public String getDomainFromServletRequest(HttpServletRequest request) {

		String domain = request.getRemoteHost();		
		if(domain == null)
			return "Domain not found!";			
		return domain;
	}
	
	// Queries geolocation API on IP - API documentation: https://ipdata.co/docs.html
	public String getClientGeolocationInfoIPv4(String ip) throws IOException{
		
		System.out.println(geoLocAPIKey);
		String geoURL = "https://api.ipdata.co/" +ip +"?api-key=" +geoLocAPIKey;
		Client client = ClientBuilder.newClient();
		Response response = client.target(geoURL)
		  .request(MediaType.TEXT_PLAIN_TYPE)
		  .header("Accept", "application/json")
		  .get();

		//System.out.println("body:" + response.readEntity(String.class));
		
		String rawClientInfo = response.readEntity(String.class);
		
		String cleanedClientInfo = rawClientInfo.replace("{", "").replace("}", "")
				.replace("[", "").replace("]", "").replace("\"", "");
		
		return cleanedClientInfo;
	}	
}
