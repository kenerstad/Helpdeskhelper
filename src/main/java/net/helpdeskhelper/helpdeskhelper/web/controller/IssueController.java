package net.helpdeskhelper.helpdeskhelper.web.controller;
import java.io.IOException;
// ## Java
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
// ## Spring
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
// ## Local
import net.helpdeskhelper.helpdeskhelper.service.IIssueService;
import net.helpdeskhelper.helpdeskhelper.service.IMailService;
import net.helpdeskhelper.helpdeskhelper.web.dto.IssueDTO;
import net.helpdeskhelper.helpdeskhelper.service.IClientInfoService;
import net.helpdeskhelper.helpdeskhelper.utils.WebUtils;



@RestController
public class IssueController {
	
	@Autowired
	IIssueService issueService; 
	
	@Autowired
	IClientInfoService clientInfoService;
	
	@Autowired
	IMailService mail;
	
	
	@GetMapping("/api/issue/getallcategories")
	public String getAllCategories() {
		
		return issueService.getIssueCategories();			
	}
	
	@PostMapping("/api/issue/getissuesfromcategory")
	public String getIssuesFromCategory(@RequestParam("id") Long catId) {
		
		return issueService.getIssuesByCategory(catId);
		
	}
	
	@PostMapping("/api/issue/getquestionsfromissue")
	public String getQuestionsFromIssues(@RequestParam("issueId") Long issueId) {
		
		
		return issueService.getQuestionsByIssue(issueId);
		
	}
	
	@PostMapping("/api/helper/submithelperform")
	public String submitHelperForm(
			@RequestParam("phoneNumber") String phoneNumber, 
			@RequestParam("issue") String issue,
			@RequestParam("questionsAndAnswers") String questionsAnd,
			HttpServletRequest req) throws IOException {
		
		List<String> clientInfo = clientInfoService.getClientInfo(req);
	
		@SuppressWarnings("unchecked")
		List<String> a = (ArrayList<String>) WebUtils.decodeJSON(questionsAnd);
		
		List<String> userInfo = new ArrayList<String>();
		
		userInfo.add("Phone number: " +phoneNumber);
		userInfo.add("Issue: " +issue);
		userInfo.addAll(a);
		userInfo.addAll(clientInfo);
		
		System.out.println(userInfo);
		
		String userInfoJSON = WebUtils.generateJSON(userInfo);
		
		mail.sendMessageToSupport(userInfo);
		
		return userInfoJSON;
	}
	
}
