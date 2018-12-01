package net.helpdeskhelper.helpdeskhelper.validator;

import org.apache.commons.validator.routines.EmailValidator;

import net.helpdeskhelper.helpdeskhelper.persistence.dao.UserRepository;
import net.helpdeskhelper.helpdeskhelper.persistence.model.UserModel;
import net.helpdeskhelper.helpdeskhelper.web.dto.UserFormDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class UserFormValidator implements Validator{

	private EmailValidator emailValidator = EmailValidator.getInstance();
	
	@Autowired
	private UserRepository userRepo;
	
	 @Override
	    public boolean supports(Class<?> clazz) {
	        return clazz == UserFormDTO.class;
	    }
	 
	 @Override
	 public void validate(Object target, Errors errors) {
		 
		 UserFormDTO userForm = (UserFormDTO) target;
		 
		 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty.UserFormDTO.userName");
		 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.UserFormDTO.firstName");
		 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.UserFormDTO.lastName");
		 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.UserFormDTO.password");
		 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmpassword", "NotEmpty.UserFormDTO.confirmPassword");
		 
		 //## Email Validation
		 if(!this.emailValidator.isValid(userForm.getEmail())) 
			 errors.rejectValue("email", "Pattern.UserFormDTO.email");
		 		 
		 else if(userRepo.findByEmail(userForm.getEmail()) != null)
			 errors.rejectValue("email", "Duplicate.UserFormDTO.email");
		 
		 //## Username Validation
		 if(!errors.hasFieldErrors("userName") && userRepo.findByUser_Name(userForm.getUserName()) != null)
			 errors.rejectValue("userName", "Duplicate.UserFormDTO.userName");
		 
		 //## Passwords match?
		 if(!errors.hasErrors() && !userForm.getConfirmPassword().equals(userForm.getPassword()))
			 errors.rejectValue("confirmPassword", "Match.UserFormDTO.confirmPassword");
	 }
}
