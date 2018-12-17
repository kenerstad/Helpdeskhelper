package net.helpdeskhelper.helpdeskhelper.config;

//## Project
import net.helpdeskhelper.helpdeskhelper.web.dto.UserFormDTO;
import net.helpdeskhelper.helpdeskhelper.validator.UserFormValidator;

//## java
import java.util.Properties;

//## Spring
import org.springframework.context.MessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;




@Configuration
public class WebConfig  implements WebMvcConfigurer {
	

	  @Bean
	    public MessageSource messageSource() {
		  
	        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();	       
	        messageSource.setBasenames("classpath:validation", 
	        						   "classpath:vardata");
	        messageSource.setDefaultEncoding("UTF-8");
	        return messageSource;
	    }
	 
	    
}
