package net.helpdeskhelper.helpdeskhelper.web.controller;

//## Project
import net.helpdeskhelper.helpdeskhelper.service.IIssueService;
import net.helpdeskhelper.helpdeskhelper.web.dto.IssueDTO;

//## Spring
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


@RestController
public class IssueController {
	
	@Autowired
	IIssueService issueService; 
	
	
	@GetMapping("/api/getallcategories")
	public String getAllCategories(Model model) {
		System.out.println("/api/getallcategories");
		
		return issueService.getIssueCategories();			
	}
}
