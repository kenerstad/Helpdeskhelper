package net.helpdeskhelper.helpdeskhelper.utils;

//## java
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//## spring
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

//## org.glassfish.jersey
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

//## Jackson
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


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
	
	public static String generateJSON(Object t) {
			
		ObjectMapper mapper = new ObjectMapper();
		String JSON = null;
		try {
			JSON = mapper.writeValueAsString(t);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return JSON;
	}
	

	public static <E> Object decodeJSON(String t) {
		
		ObjectMapper mapper = new ObjectMapper();
		Object a = null;
		try {
			a = mapper.readValue(t, Object.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return a;
	}
}
