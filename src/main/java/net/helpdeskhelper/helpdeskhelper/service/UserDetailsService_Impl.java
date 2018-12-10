package net.helpdeskhelper.helpdeskhelper.service;

// ##Default lib imports
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// ##Project imports
import net.helpdeskhelper.helpdeskhelper.persistence.dao.UserRepository;
import net.helpdeskhelper.helpdeskhelper.persistence.dao.RoleRepository;
import net.helpdeskhelper.helpdeskhelper.persistence.model.UserModel;
import net.helpdeskhelper.helpdeskhelper.persistence.model.RoleModel;

// ##Spring imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class UserDetailsService_Impl implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Override
	public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
		
		UserModel user = this.userRepo.findByEmail(email);
		
		if(user == null) {
			System.out.println("User not found! " +email);
			throw new UsernameNotFoundException("User " + email + " was not found in the database");
		}
		
		System.out.println("Found User: " + email);
				
		UserDetails userDetails = (UserDetails) new User(user.getUserName(), user.getEncryptedPassword(), getGrantedAuthorities(user.getRoles()));
		return userDetails;
		
	}
	
	 private List<GrantedAuthority> getGrantedAuthorities(Collection<RoleModel> roles) {
		 
	        List<GrantedAuthority> authorities = new ArrayList<>();	        
	        for (RoleModel role : roles) {	        	        	
	            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
	        }
	        return authorities;
	    }
	
}
