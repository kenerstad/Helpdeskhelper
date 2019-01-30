package net.helpdeskhelper.helpdeskhelper.web.controller;
// ## Java
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
// ## Spring
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
// ## Project
import net.helpdeskhelper.helpdeskhelper.service.IIssueService;
import net.helpdeskhelper.helpdeskhelper.web.dto.IssueDTO;




@RestController
public class IssueController {
	
	@Autowired
	IIssueService issueService; 
	
	
	@GetMapping("/api/issue/getallcategories")
	public String getAllCategories(Model model) {
		
		return issueService.getIssueCategories();			
	}
	
	@PostMapping("/api/issue/getIssuesFromCategory")
	public String getIssuesFromCategory(@RequestParam("category") String category) {
		
		return issueService.getIssuesByCategory(category);
		
	}
}
