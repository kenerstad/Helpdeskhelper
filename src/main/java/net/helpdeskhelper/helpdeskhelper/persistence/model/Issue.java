package net.helpdeskhelper.helpdeskhelper.persistence.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	private String issueName;
	
	@NotNull
	@Size(max = 150)
	private String issueDescription;
		

	//## Relational	
	@OneToMany(
			cascade = CascadeType.REMOVE, 
			orphanRemoval = true, 
			fetch = FetchType.LAZY, 
			mappedBy = "issue")
	private List<Question> questions;
	@ManyToOne
	@JoinColumn(name="categoryId", nullable=false)
	private IssueCategory issueCategory;
	
	
	public Long getIssueId() {
		return issueId;
	}

	public void setIssueId(Long issueId) {
		this.issueId = issueId;
	}
	
	public String getIssueName() {
		return issueName;
	}

	public void setIssueName(String issueName) {
		this.issueName = issueName;
	}
	
	public String getIssueDescription() {
		return issueDescription;
	}

	public void setIssueDescription(String issueDescription) {
		this.issueDescription = issueDescription;
	}

	public IssueCategory getIssueCategory() {
		return issueCategory;
	}

	public void setIssueCategory(IssueCategory issueCategory) {
		this.issueCategory = issueCategory;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	public void addQuestion(Question question) {
		questions.add(question);
	}
	
}

