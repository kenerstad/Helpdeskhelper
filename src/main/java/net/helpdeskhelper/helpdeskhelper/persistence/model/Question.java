package net.helpdeskhelper.helpdeskhelper.persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "question")

public class Question {

	@Id
    @GeneratedValue
    @NotNull
    private Long questionID;
	
	@NotNull
	@Size(max = 200)
	private String question;
		
	@ManyToOne
	@JoinColumn(name="issueId", nullable=false)
	private Issue issue;

	public Long getQuestionID() {
		return questionID;
	}

	public void setQuestionID(Long questionID) {
		this.questionID = questionID;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}
	
	
}
