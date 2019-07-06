package net.helpdeskhelper.helpdeskhelper.service;

//## Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//## Project
import net.helpdeskhelper.helpdeskhelper.web.dto.UserFormDTO;
import net.helpdeskhelper.helpdeskhelper.persistence.dao.UserRepository;
import net.helpdeskhelper.helpdeskhelper.persistence.dao.RoleRepository;
import net.helpdeskhelper.helpdeskhelper.persistence.model.UserModel;
import net.helpdeskhelper.helpdeskhelper.persistence.model.RoleModel;
import net.helpdeskhelper.helpdeskhelper.utils.EncryptPasswordUtil;


/*
 * Custom service for managing of users.
 */
@Service
public class UserService implements IUserService{
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	RoleRepository roleRepo;
	
	/*
	 * Register a new user from a user DTO generated from frontend user registration.
	 * Uses custom utility class EncryptPasswordUtil to encrypt user defined password.
	 * Sets used defined username, email, first/last name and which predefined roles 
	 * the user should have. Saves the new user to user repository/DB.
	 */
	public void registerUser(UserFormDTO userForm) {
				
		UserModel user = new UserModel();
		user.setUserName(userForm.getUserName());
		user.setEmail(userForm.getEmail());
		user.setFirstName(userForm.getFirstName());
		user.setLastName(userForm.getLastName());
		user.setEncryptedPassword(EncryptPasswordUtil.encryptPassword(userForm.getPassword()));
		user.setEnabled(false);
		user.addRole(roleRepo.findByRoleName("ROLE_USER"));
		user.addRole(roleRepo.findByRoleName("ROLE_ADMIN"));
		userRepo.save(user);
		
		
	}
	
	/*
	 * Generates and registers a default admin user for testing purposes.
	 */
	public String generateDefaultUser() {
		
		UserModel user = new UserModel();
		user.setUserName("kris");
		user.setEmail("kenerstad2@gmail.com");
		user.setFirstName("Kristoffer");
		user.setLastName("Enerstad");
		user.setEncryptedPassword(EncryptPasswordUtil.encryptPassword("a"));
		user.setEnabled(false);
		user.addRole(roleRepo.findByRoleName("ROLE_USER"));
		user.addRole(roleRepo.findByRoleName("ROLE_ADMIN"));
		try {
			userRepo.save(user);
		}
		catch (Exception e) {
			return "exception: " +e;
		}
		
		return "DEFAULT USER 'kris' CREATED SUCCESSFULLY";
	}
	
	/*
	 * Generates default roles.
	 */
	public String generateDefaultRoles() {
		
		RoleModel role = new RoleModel();
		role.setRoleName("ROLE_USER");
			
		RoleModel role2 = new RoleModel();
		role2.setRoleName("ROLE_ADMIN");
		
		roleRepo.save(role);
		roleRepo.save(role2);
		
		return "ROLE_USER, ROLE ADMIN CREATED SUCCESSFULLY";
	}
}
