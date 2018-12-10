package net.helpdeskhelper.helpdeskhelper.persistence.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
 
@Entity
@Table(name = "role")

public class RoleModel {   
	
    @Id
    @GeneratedValue
    @NotNull
    private Long roleId;
 
    @NotNull
    @Size(max = 36)
    private String roleName;
 
    
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    private Set<UserModel> users = new HashSet<>();
    
    
    // ## GETTERS/SETTERS ##
    
	public Long getRoleId() {
        return roleId;
    }
 
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
 
    public String getRoleName() {
        return roleName;
    }
 
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public Set<UserModel> getUsers() {
  		return users;
  	}

  	public void setUsers(Set<UserModel> users) {
  		this.users = users;
  	}
     
}
