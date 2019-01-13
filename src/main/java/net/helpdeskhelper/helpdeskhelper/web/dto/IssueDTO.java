package net.helpdeskhelper.helpdeskhelper.web.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IssueDTO {
	
	private String issueName;
	
	private String issueCategory;

	private List<String> questions;
	
	public IssueDTO() {
		
	}
	
	public String getIssueName() {
		return issueName;
	}

	public void setIssueName(String issueName) {
		this.issueName = issueName;
	}

	public String getIssueCategory() {
		return issueCategory;
	}

	public void setIssueCategory(String issueCategory) {
		this.issueCategory = issueCategory;
	}

	public Collection<String> getQuestions() {
		return questions;
	}

	public void setQuestions(Collection<String> questions) {
		this.questions = (List<String>) questions;
	}
}
