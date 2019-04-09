package net.helpdeskhelper.helpdeskhelper.service;

import java.util.List;
import java.util.Set;

import net.helpdeskhelper.helpdeskhelper.web.dto.IssueDTO;

public interface IIssueService {

	String getIssueCategories();
	String getIssuesByCategory(Long catId);
	String getQuestionsByIssue(Long issueId);
	String addCategory(String category);
	String updateCategory(Long id, String catName, String catNameChange);
	String deleteCategory(Long id);
	String addIssueToCategory(Long catId, String issueName, String issueDescription);
	String updateIssue(Long id, String issueName, String issueDescription);
	String deleteIssue(Long id);
	String addQuestionToIssue(Long issueId, String questionText);
	String deleteQuestion(Long id);
	String updateQuestion(Long id, String questionChange);
	
	void generateTemplates();

}
