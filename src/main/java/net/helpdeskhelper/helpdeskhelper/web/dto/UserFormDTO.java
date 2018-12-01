package net.helpdeskhelper.helpdeskhelper.web.dto;

/*
DTO that represents the user's registration info.
*/
public class UserFormDTO {
	
	private String userName;
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private String confirmPassword;
	
	
	public UserFormDTO() {
		
	}
	
	
	public UserFormDTO(String userName, String firstName, 
			String lastName, String password, String confirmPassword) {
		
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.confirmPassword = confirmPassword;		
	}
	
	
	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getConfirmPassword() {
		return confirmPassword;
	}


	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
}
