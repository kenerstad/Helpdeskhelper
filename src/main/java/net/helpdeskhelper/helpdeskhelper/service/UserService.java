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



@Service
public class UserService implements IUserService{
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	RoleRepository roleRepo;
	
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
