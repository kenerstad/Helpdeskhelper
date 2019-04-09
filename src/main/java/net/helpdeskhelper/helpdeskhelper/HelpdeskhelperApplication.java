package net.helpdeskhelper.helpdeskhelper;

//# Local
import net.helpdeskhelper.helpdeskhelper.service.IUserService;
import net.helpdeskhelper.helpdeskhelper.service.IIssueService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class HelpdeskhelperApplication implements CommandLineRunner{

	@Autowired
	IUserService userService;
	
	@Autowired
	IIssueService issueService;
	
	public static void main(String[] args) {
		SpringApplication.run(HelpdeskhelperApplication.class, args);
	}
	
    @Override
    public void run(String... args) throws Exception {

    	System.out.println(userService.generateDefaultRoles());
    	System.out.println(userService.generateDefaultUser());    
    	issueService.generateTemplates();
    }   
}
