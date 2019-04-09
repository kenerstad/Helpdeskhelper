package net.helpdeskhelper.helpdeskhelper.web.controller;

//## Java
import java.security.Principal;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

//## Project
import net.helpdeskhelper.helpdeskhelper.utils.WebUtils;
import net.helpdeskhelper.helpdeskhelper.web.dto.UserFormDTO;
import net.helpdeskhelper.helpdeskhelper.validator.UserFormValidator;
import net.helpdeskhelper.helpdeskhelper.service.IUserService;
import net.helpdeskhelper.helpdeskhelper.service.IClientInfoService;
import net.helpdeskhelper.helpdeskhelper.service.IMailService;

//## Spring
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.beans.factory.annotation.Autowired;


@Controller
public class MainController {
		
	@Autowired
	UserFormValidator userFormValidator;
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IClientInfoService clientInfoService;
	
	@Autowired
	IMailService mailService;

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
	public String showWelcomePage(@RequestParam(name="status", required = false) String status,
			Model model) {
		if(status != null) {
			if(status.equals("logout")) {
				System.out.println("status = logout");
				model.addAttribute("title", appTitle);
				model.addAttribute("description", "Successfully logged out");
			}
		}				
		return "index";
	}
	
	@GetMapping("/register")
	public String showRegistrationPage(Model model) {
		
		UserFormDTO userForm = new UserFormDTO();
		model.addAttribute("userForm", userForm);
		return "register";
	}
	
	@PostMapping("/register")
	public String saveRegistration(Model model, 
			@ModelAttribute("userForm") 
			@Validated UserFormDTO userForm, 
			BindingResult result) {
		
		 if (result.hasErrors()) 
	         return "/register";
	      
		 userService.registerUser(userForm);		 
		 return "registerSuccess";
	}
	
	public String showHelperPage(Model model, HttpServletRequest req) {
		
		
		return "helperpage";
	}
		
	@GetMapping("/helper")
	public String showHelperPage(Model model) {
		return "helper";
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
	
	@GetMapping("/admin_") 
	public String showAdminPage() {
		
		return "admin/adminoverview";
	}
	
	@GetMapping("/logoutSuccessful")
	public String showLogoutSuccessfulPage(Model model) {
		
		model.addAttribute("description", logoutPageDesc);		
		return "logoutSuccessful";
	}
}
