package net.helpdeskhelper.helpdeskhelper.web.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IssueDTO {
	
	private Long id;
	
	private String issueName;
	
	private String issueDescription;

	private List<String> questions;
	
	public IssueDTO() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getIssueName() {
		return issueName;
	}

	public void setIssueName(String issueName) {
		this.issueName = issueName;
	}

	public String getissueDescription() {
		return issueDescription;
	}

	public void setissueDescription(String issueDescription) {
		this.issueDescription = issueDescription;
	}

	public Collection<String> getQuestions() {
		return questions;
	}

	public void setQuestions(Collection<String> questions) {
		this.questions = (List<String>) questions;
	}
}