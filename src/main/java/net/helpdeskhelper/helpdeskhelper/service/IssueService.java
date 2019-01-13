package net.helpdeskhelper.helpdeskhelper.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.helpdeskhelper.helpdeskhelper.persistence.dao.IssueRepository;
import net.helpdeskhelper.helpdeskhelper.persistence.dao.QuestionRepository;
import net.helpdeskhelper.helpdeskhelper.web.dto.IssueDTO;
import net.helpdeskhelper.helpdeskhelper.persistence.model.Issue;
import net.helpdeskhelper.helpdeskhelper.persistence.model.Question;

@Service
public class IssueService implements IIssueService{

	@Autowired
	IssueRepository issueRepo;
	
	@Autowired
	QuestionRepository questionRepo;
	
	public void registerNewIssue() {
		
	}
	
	@SuppressWarnings("null")
	public Set<String> getIssueCategories(){
		
		Set<String> categories = null;
		List<Issue> issues = (List<Issue>) issueRepo.findAll();
		
		for (Issue issue : issues) {
			categories.add(issue.getIssueCategory());
		}
		return categories;
	}
	
	public IssueDTO getIssueQuestions(String issueName) {
		
		Issue issue = this.issueRepo.findByIssueName(issueName);
		IssueDTO issueDTO = new IssueDTO();
		issueDTO.setQuestions(convertQuestionsToStrings(issue.getQuestions()));
		return issueDTO;
	}
	
	
	
	public List<String> convertQuestionsToStrings(Collection<Question> questions) {
		
		List<String> questionStrings = new ArrayList<String>();
		for (Question question : questions) {
			questionStrings.add(question.getQuestion());
		}
		return questionStrings;
	}
	
}
