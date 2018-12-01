package net.helpdeskhelper.helpdeskhelper.config;

//## Project
import net.helpdeskhelper.helpdeskhelper.web.dto.UserFormDTO;
import net.helpdeskhelper.helpdeskhelper.validator.UserFormValidator;

//## Spring
import org.springframework.context.MessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;




@Configuration
public class WebConfig  implements WebMvcConfigurer {
	
	@Autowired
	UserFormValidator userFormValidator;

	  @Bean
	    public MessageSource messageSource() {
		  
	        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();	       
	        messageSource.setBasename("classpath:validation");
	        messageSource.setDefaultEncoding("UTF-8");
	        return messageSource;
	    }
	  
	  @InitBinder
	   protected void initBinder(WebDataBinder dataBinder) {

	      Object target = dataBinder.getTarget();
	      if (target == null) 
	         return;
	      
	      System.out.println("Target=" + target);
	 
	      if (target.getClass() == UserFormDTO.class) 
	         dataBinder.setValidator(userFormValidator);	      
	   }

}
