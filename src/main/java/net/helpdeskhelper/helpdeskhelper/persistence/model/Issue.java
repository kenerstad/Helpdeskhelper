package net.helpdeskhelper.helpdeskhelper.persistence.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "issue")

public class Issue {

	@Id
    @GeneratedValue
    @NotNull
    private Long issueId;
	
	@NotNull
	@Size(max = 36)
	private String issueCategory;
	
	@NotNull
	@Size(max = 36)
	private String issueName;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "issue")
	private Set<Question> questions;

	
	public Long getIssueId() {
		return issueId;
	}

	public void setIssueId(Long issueId) {
		this.issueId = issueId;
	}

	public String getIssueCategory() {
		return issueCategory;
	}

	public void setIssueCategory(String issueCategory) {
		this.issueCategory = issueCategory;
	}

	public String getIssueName() {
		return issueName;
	}

	public void setIssueName(String issueName) {
		this.issueName = issueName;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}
	
	
	
}

