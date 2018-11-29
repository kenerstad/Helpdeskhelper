package net.helpdeskhelper.helpdeskhelper.service;

// ##Default lib imports
import java.util.ArrayList;
import java.util.List;

// ##Project imports
import net.helpdeskhelper.helpdeskhelper.persistence.dao.UserRepository;
import net.helpdeskhelper.helpdeskhelper.persistence.dao.RoleRepository;
import net.helpdeskhelper.helpdeskhelper.persistence.dao.UserRoleRepository;
import net.helpdeskhelper.helpdeskhelper.persistence.model.UserModel;
import net.helpdeskhelper.helpdeskhelper.persistence.model.UserRoleModel;

// ##Spring imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class UserDetailsService_Impl implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private UserRoleRepository UserRoleRepo;
	
	
	@Override
	public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
		
		UserModel user = this.userRepo.findByEmail(email);
		
		if(user == null) {
			System.out.println("User not found! " +email);
			throw new UsernameNotFoundException("User " + email + " was not found in the database");
		}
		
		System.out.println("Found User: " + email);
		
		List<String> userRoleNames = new ArrayList<String>();
		List<UserRoleModel> userRoleModels = this.UserRoleRepo.findByUser(user.getUserName());
		List<GrantedAuthority> grantedAuthList = new ArrayList<GrantedAuthority>();
		 
		for(UserRoleModel roleModel : userRoleModels) {
			userRoleNames.add(roleModel.getRole().getRoleName());
		}
		
		if(userRoleNames != null) {
			for (String role : userRoleNames) {
				GrantedAuthority auth = new SimpleGrantedAuthority(role);
				grantedAuthList.add(auth);
			}
		}
		
		UserDetails userDetails = (UserDetails) new User(user.getUserName(), user.getEncryptedPassword(), grantedAuthList);
		return userDetails;
	}
	
}
