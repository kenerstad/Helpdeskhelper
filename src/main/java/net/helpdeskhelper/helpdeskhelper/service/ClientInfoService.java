package net.helpdeskhelper.helpdeskhelper.service;

//## Java
import java.io.IOException;
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

	public String getClientInfo(HttpServletRequest req) throws IOException{
		
		//String ip = getIPFromServletRequest(req);
		String geoLocInfo = getGeolocationInfoIPv4("84.212.9.174");
		
		return geoLocInfo;
	}
	
	// Checks for proxy forwarded IP adress, if none, gets actual IP address.
	public String getIPFromServletRequest(HttpServletRequest req) {
		
		String ipAddress = req.getHeader("x-forwarded-for");
        if (ipAddress == null) {
            ipAddress = req.getHeader("X_FORWARDED_FOR");
            if (ipAddress == null)
                ipAddress = req.getRemoteAddr();            
        }    
        return ipAddress;
	}	
	
	// Gets domain name from servlet request.
	public String getDomainFromServletRequest(HttpServletRequest req) {
		
		String domain = req.getRemoteHost();		
		if(domain == null)
			return "Domain not found!";			
		return domain;
	}
	
	// Queries geolocation API on IP - API documentation: https://ipdata.co/docs.html
	public String getGeolocationInfoIPv4(String ip) throws IOException{
		
		System.out.println(geoLocAPIKey);
		String geoURL = "https://api.ipdata.co/" +ip +"?api-key=" +geoLocAPIKey;
		Client client = ClientBuilder.newClient();
		Response response = client.target(geoURL)
		  .request(MediaType.TEXT_PLAIN_TYPE)
		  .header("Accept", "application/json")
		  .get();

		//System.out.println("body:" + response.readEntity(String.class));
		return response.readEntity(String.class);
	}
	
}
