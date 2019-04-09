package net.helpdeskhelper.helpdeskhelper.service;

//## Java
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
//## Spring
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//## Project
import net.helpdeskhelper.helpdeskhelper.utils.WebUtils;


@Service
public class ClientInfoService implements IClientInfoService{
		
	@Value("${geoLoc.APIKey}")
	private String geoLocAPIKey;

	public List<String> getClientInfo(HttpServletRequest request) throws IOException{
		
		//String ip = getClientIP(request);
		//String geoLocInfo = getClientGeolocationInfoIPv4("84.212.9.174");
		String geoLocInfo = "here goes the geoloc info.";
		List<String> clientInfo = new ArrayList<String>();
		clientInfo.add("Browser: " +getClientBrowser(request));
		clientInfo.add("OS: " +getClientOS(request));
		clientInfo.add(geoLocInfo);
		
		return clientInfo;
	}
	

			
	// Checks for proxy forwarded IP adress, if none, gets actual IP address.
	private String getClientIP(HttpServletRequest request) {
		
		String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null) {
            ipAddress = request.getHeader("X_FORWARDED_FOR");
            if (ipAddress == null)
                ipAddress = request.getRemoteAddr();            
        }    
        return ipAddress;
	}	
	
	private String getClientBrowser(HttpServletRequest request) {
		
	     final String browserDetails = request.getHeader("User-Agent");
	     
	        String  userAgent       =   browserDetails;
	        String  user            =   userAgent.toLowerCase();

	        String browser = "";

	     if (user.contains("msie")) {
	            String substring=userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
	            browser=substring.split(" ")[0].replace("MSIE", "IE")+"-"+substring.split(" ")[1];
	        } 
	     
	     else if (user.contains("safari") && user.contains("version")) {
	            browser=(userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0]+"-"+(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
	        } 
	     
	     else if ( user.contains("opr") || user.contains("opera")) {
	            if(user.contains("opera"))
	                browser=(userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0]+"-"+(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
	            else if(user.contains("opr"))
	                browser=((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-")).replace("OPR", "Opera");
	        } 
	     
	     else if (user.contains("chrome")) {
	            browser=(userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
	        } 
	     
	     else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1)  || (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1) || (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1) ) {
	            //browser=(userAgent.substring(userAgent.indexOf("MSIE")).split(" ")[0]).replace("/", "-");
	            browser = "Netscape-?";

	        } 
	     
	     else if (user.contains("firefox")) {
	            browser=(userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
	        } 
	     
	     else if(user.contains("rv")) {
	            browser="IE-" + user.substring(user.indexOf("rv") + 3, user.indexOf(")"));
	        } 
	     else {
	            browser = "UnKnown, More-Info: "+userAgent;
	        }
	     
	     return browser;
	}
	
	private String getClientOS(HttpServletRequest request) {
		
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
	private String getDomainFromServletRequest(HttpServletRequest request) {

		String domain = request.getRemoteHost();		
		if(domain == null)
			return "Domain not found!";			
		return domain;
	}
	
	// Queries geolocation API on IP - API documentation: https://ipdata.co/docs.html
	private String getClientGeolocationInfoIPv4(String ip) throws IOException{
		
		System.out.println(geoLocAPIKey);
		String geoURL = "https://api.ipdata.co/" +ip +"?api-key=" +geoLocAPIKey;
		Client client = ClientBuilder.newClient();
		Response response = client.target(geoURL)
		  .request(MediaType.TEXT_PLAIN_TYPE)
		  .header("Accept", "application/json")
		  .get();

		//System.out.println("body:" + response.readEntity(String.class));
		
		String rawClientGeoLocInfo = response.readEntity(String.class);
	
		String cleanedClientInfo = rawClientGeoLocInfo.replace("{", "").replace("}", "")
				.replace("[", "").replace("]", "").replace("\"", "");
		
	
	
		
		return cleanedClientInfo;
		//return cleanedClientInfo;
	}	
}
