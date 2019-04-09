package net.helpdeskhelper.helpdeskhelper.web.dto;

public class AdminFeedbackDTO {

	private String name;
	private String previousName;
	private String nameChange;
	private String previousDescription;
	private String descriptionChange;
	private String msg;
	private Long id;
	
	public AdminFeedbackDTO() {
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPreviousName() {
		return previousName;
	}
	public void setPreviousName(String previousName) {
		this.previousName = previousName;
	}
	public String getNameChange() {
		return nameChange;
	}
	public void setNameChange(String nameChange) {
		this.nameChange = nameChange;
	}
	public String getPreviousDescription() {
		return previousDescription;
	}
	public void setPreviousDescription(String previousDescription) {
		this.previousDescription = previousDescription;
	}
	public String getDescriptionChange() {
		return descriptionChange;
	}
	public void setDescriptionChange(String descriptionChange) {
		this.descriptionChange = descriptionChange;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String errorMsg) {
		this.msg = errorMsg;
	}
	
}
	
