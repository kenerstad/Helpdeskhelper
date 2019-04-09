package net.helpdeskhelper.helpdeskhelper.web.controller;

//## Java
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
//## Spring
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
//## Local
import net.helpdeskhelper.helpdeskhelper.service.IIssueService;
import net.helpdeskhelper.helpdeskhelper.service.IMailService;
import net.helpdeskhelper.helpdeskhelper.web.dto.IssueDTO;
import net.helpdeskhelper.helpdeskhelper.service.IClientInfoService;
import net.helpdeskhelper.helpdeskhelper.utils.WebUtils;


@RestController
public class AdminController {
	
	@Autowired
	IIssueService issueService; 
			
	@PostMapping("/api/admin/addcategory")
	public String addCategory (@RequestParam("category") String cat) {
		
		return issueService.addCategory(cat);
		
	}
	
	@PostMapping("/api/admin/alterCategory")
	public String alterCategory (
			@RequestParam("categoryId") Long id, 
			@RequestParam("category") String catName, 
			@RequestParam("categoryChange") String catNameChange) {
		
		return issueService.updateCategory(id, catName, catNameChange);		
	}
	
	@PostMapping("/api/admin/deletecategory")
	public String deleteCategory (
			@RequestParam("id") Long id) {
		
		return issueService.deleteCategory(id);		
	}
	
	@PostMapping("/api/admin/addissuetocategory")
	public String addIssueToCategory (
			@RequestParam("categoryId") Long catId,
			@RequestParam("issueName") String issueName,
			@RequestParam("issueDescription") String issueDescription) {
				
		return issueService.addIssueToCategory(catId, issueName, issueDescription);		
	}
	
	@PostMapping("/api/admin/alterissue")
	public String alterIssue (
			@RequestParam("issueId") Long id, 
			@RequestParam("issueName") String issueName, 
			@RequestParam("issueDescription") String issueDescription) {
		
		return issueService.updateIssue(id, issueName, issueDescription);		
	}
	
	@PostMapping("/api/admin/deleteissue")
	public String deleteIssue (
			@RequestParam("id") Long id) {
		
		return issueService.deleteIssue(id);		
	}
	
	@PostMapping("/api/admin/addQuestionToIssue")
	public String addQuestionToIssue (
			@RequestParam("issueId") Long issueId,
			@RequestParam("questionText") String questionText) {
				
		return issueService.addQuestionToIssue(issueId, questionText);		
	}
	
	@PostMapping("/api/admin/alterquestion")
	public String alterQuestion (
			@RequestParam("questionId") Long questionId, 
			@RequestParam("questionChange") String questionChange) {
		
		return issueService.updateQuestion(questionId, questionChange);		
	}
	
	@PostMapping("/api/admin/deletequestion")
	public String deleteQuestion (
			@RequestParam("id") Long id) {
		
		return issueService.deleteQuestion(id);		
	}
}
