package net.helpdeskhelper.helpdeskhelper.persistence.model;

import javax.persistence.Column;
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
    private Long questionId;
		
	@NotNull
	@Size(max = 200)
	private String question;
		
	@ManyToOne
	@JoinColumn(name="issueId", nullable=true)
	private Issue issue;

	public Long getQuestionID() {
		return questionId;
	}

	public void setQuestionID(Long questionID) {
		this.questionId = questionID;
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
