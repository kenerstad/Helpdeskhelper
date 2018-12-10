package net.helpdeskhelper.helpdeskhelper.persistence.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.JoinColumn;


@Entity
@Table(name = "user")

public class UserModel {

	@Id
    @GeneratedValue
    @NotNull
    private Long userId;
 
    @NotNull
    @Size(max = 36)
    private String userName;
    
    @NotNull
    @Size(max = 36)
    private String email;
    
    @NotNull
    @Size(max = 36)
    private String firstName;
    
    @NotNull
    @Size(max = 36)
    private String lastName;
 
    @NotNull
    @Size(max = 128)
    private String encryptedPassword;
 
    @NotNull
    private boolean enabled;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = { @JoinColumn(
            		name = "User_Id", referencedColumnName = "userId") },
            inverseJoinColumns = { @JoinColumn(
            		name = "role_id", referencedColumnName = "roleId") })
    private Set<RoleModel> roles = new HashSet<>();
    
    
    // ## GETTERS/SETTERS ##
    
	public Long getUserId() {
        return userId;
    }
 
    public void setUserId(Long userId) {
        this.userId = userId;
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
 
    public String getEncryptedPassword() {
        return encryptedPassword;
    }
 
    public void setEncryptedPassword(String encrytedPassword) {
        this.encryptedPassword = encrytedPassword;
    }
 
    public boolean isEnabled() {
        return enabled;
    }
 
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    public Set<RoleModel> getRoles() {
  		return roles;
  	}

  	public void setRoles(Set<RoleModel> roles) {
  		this.roles = roles;
  	}
  	
  	public void addRole(RoleModel role) {
  		roles.add(role);
  	}

}
