package net.helpdeskhelper.helpdeskhelper.service;

import net.helpdeskhelper.helpdeskhelper.web.dto.UserFormDTO;

public interface IUserService {
	
	void registerUser(UserFormDTO userForm);
	String generateDefaultUser();
	String generateDefaultRoles();
}
