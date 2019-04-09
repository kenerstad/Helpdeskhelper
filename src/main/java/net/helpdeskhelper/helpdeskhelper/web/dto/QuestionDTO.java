package net.helpdeskhelper.helpdeskhelper.web.dto;

public class QuestionDTO {
	
	private long id;

	private String questionText;
	
	public QuestionDTO() {
		
		
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

}
