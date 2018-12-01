package net.helpdeskhelper.helpdeskhelper.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;



@Entity
@Table(name = "user_role", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "USER_ROLE_UK", columnNames = { "User_Id", "Role_Id" }) })

public class UserRoleModel {

	@Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_Id", nullable = false)
    private UserModel user;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Role_Id", nullable = false)
    private RoleModel role;
 
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public UserModel getUser() {
        return user;
    }
 
    public void setUser(UserModel user) {
        this.user = user;
    }
 
    public RoleModel getRole() {
        return role;
    }
 
    public void setRole(RoleModel role) {
        this.role = role;
    }
}
