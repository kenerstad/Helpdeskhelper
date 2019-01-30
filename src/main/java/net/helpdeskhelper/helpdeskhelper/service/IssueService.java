package net.helpdeskhelper.helpdeskhelper.service;
// ## Java
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
// ## Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// ## Project
import net.helpdeskhelper.helpdeskhelper.persistence.dao.IssueRepository;
import net.helpdeskhelper.helpdeskhelper.persistence.dao.IssueCategoryRepository;
import net.helpdeskhelper.helpdeskhelper.persistence.dao.QuestionRepository;
import net.helpdeskhelper.helpdeskhelper.persistence.model.Issue;
import net.helpdeskhelper.helpdeskhelper.persistence.model.Question;
import net.helpdeskhelper.helpdeskhelper.persistence.model.IssueCategory;
import net.helpdeskhelper.helpdeskhelper.utils.WebUtils;
import net.helpdeskhelper.helpdeskhelper.web.dto.CategoryDTO;
import net.helpdeskhelper.helpdeskhelper.web.dto.IssueDTO;


@Service
public class IssueService implements IIssueService{

	@Autowired
	IssueRepository issueRepo;
	
	@Autowired
	QuestionRepository questionRepo;
	
	@Autowired
	IssueCategoryRepository issueCategoryRepo;
	
	public void registerNewIssue() {
		
	}
	
	@SuppressWarnings("null")
	public String getIssueCategories(){
		/*
		Set<String> categories = null;
		List<Issue> issues = (List<Issue>) issueRepo.findAll();
		
		for (Issue issue : issues) {
			categories.add(issue.getIssueCategory());
		}
		
		return categories;
		*/
		List<IssueCategory> issueCategories = (List<IssueCategory>) issueCategoryRepo.findAll();
		
		List<CategoryDTO> categories = new ArrayList<CategoryDTO>();
				
		for(IssueCategory issueCategory : issueCategories) {
			CategoryDTO categoryDTO = new CategoryDTO();
			categoryDTO.setCategoryName(issueCategory.getCategoryName());
			categoryDTO.setCategoryDescription(issueCategory.getCategoryDescription());
			categories.add(categoryDTO);
		}

		String stuff = WebUtils.generateJSON(categories);
		System.out.println(stuff);
		return stuff;
		 
	}
	
	public String getIssuesByCategory(String category){
		
		System.out.println(category);
		
		IssueCategory issueCategory = issueCategoryRepo.findByCategoryName(category);
		List<Issue> issues = issueCategory.getIssues();
		return WebUtils.generateJSON(convertIssuesFromCategoryToString(issues));
		
		//return WebUtils.generateJSON(category);
	}
	
	public IssueDTO getIssueQuestions(String issueName) {
		
		Issue issue = this.issueRepo.findByIssueName(issueName);
		IssueDTO issueDTO = new IssueDTO();
		issueDTO.setQuestions(convertQuestionsToStrings(issue.getQuestions()));
		return issueDTO;
	}
	
	
	private List<IssueDTO> convertIssuesFromCategoryToString(Collection<Issue> issues) {
		
		List<IssueDTO> issueList = new ArrayList<IssueDTO>();
		for (Issue issue : issues) {
			IssueDTO issueDTO = new IssueDTO();
			issueDTO.setIssueName(issue.getIssueName());
			issueDTO.setissueDescription(issue.getIssueDescription());
			issueList.add(issueDTO);
		}
		return issueList;
	}
	
	private List<String> convertQuestionsToStrings(Collection<Question> questions) {
		
		List<String> questionStrings = new ArrayList<String>();
		for (Question question : questions) {
			questionStrings.add(question.getQuestion());
		}
		return questionStrings;
	}
	
	public void generateIssues() {
		generateCategories();
		Issue issue = new Issue();
		issue.setIssueName("issue1");
		issue.setIssueDescription("Description for issue1");
		issue.setIssueCategory(issueCategoryRepo.findByCategoryName("Category1"));
		issueRepo.save(issue);
		generateQuestions(issue);
	}
	
	private void generateCategories() {
		
		IssueCategory category = new IssueCategory();
		category.setCategoryName("Category1");
		category.setCategoryDescription("blablablablablablablablalba");
		issueCategoryRepo.save(category);
		
		IssueCategory category2 = new IssueCategory();
		category2.setCategoryName("Category2");
		category2.setCategoryDescription("blablablablabladsadsadsadsadwqdq3323r32r32r23r23blablablalba");
		issueCategoryRepo.save(category2);
		System.out.println("CATEGORIES GENERATED SUCCESSFULLY");
	}
	
	private void generateQuestions(Issue issue) {
		Question question1 = new Question();
		question1.setIssue(issue);
		question1.setQuestion("Issue1_Question1");
		Question question2 = new Question();
		question2.setQuestion("Issue1_Question2");
		question2.setIssue(issue);
		questionRepo.save(question1);
		questionRepo.save(question2);
		
		System.out.println("QUESTIONS GENERATED SUCCESSFULLY");
	}
	
}
