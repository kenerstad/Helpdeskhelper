package net.helpdeskhelper.helpdeskhelper.web.controller;

//## Java standard
import java.security.Principal;
import java.util.List;

//## Project
import net.helpdeskhelper.helpdeskhelper.utils.WebUtils;
import net.helpdeskhelper.helpdeskhelper.web.dto.UserFormDTO;
import net.helpdeskhelper.helpdeskhelper.validator.UserFormValidator;

import org.springframework.beans.factory.annotation.Autowired;
//## Spring
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class MainController {
	
	@Autowired
	UserFormValidator userFormValidator;

	@Value("${app.title}")
	private String appTitle;
	
	@Value("${indexpage.description}")
	private String indexPageDesc;
	
	@Value("${logoutpage.description}")
	private String logoutPageDesc;
	
	
  @InitBinder
   protected void initBinder(WebDataBinder dataBinder) {

      Object target = dataBinder.getTarget();
      if (target == null) 
         return;
      
      System.out.println("Target=" + target);
 
      if (target.getClass() == UserFormDTO.class) 
         dataBinder.setValidator(userFormValidator);	      
   }
	
	@GetMapping("/")
	public String showWelcomePage(Model model) {
		model.addAttribute("title", appTitle);
		model.addAttribute("description", indexPageDesc);
		return "welcome";
	}
	
	
	
	//## SECURITY ##
	
	@GetMapping("/login")
	public String showLoginPage(Model model) {
		return "login";
	}
	
	/* 	SHows this page after successful logon attempt.
		gets currently authenticated user and supplies account page
		with information about it */	
	@GetMapping("/account") 
	public String showAccountPage(Model model) {
		
		User authenticatedUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<String> userInfo = WebUtils.userInfoToList(authenticatedUser);
		model.addAttribute("userInfo", userInfo);
		return "account";
	}
	
	@GetMapping("/logoutSuccessful")
	public String showLogoutSuccessfulPage(Model model) {
		
		model.addAttribute("description", logoutPageDesc);		
		return "logoutSuccessful";
	}
}
