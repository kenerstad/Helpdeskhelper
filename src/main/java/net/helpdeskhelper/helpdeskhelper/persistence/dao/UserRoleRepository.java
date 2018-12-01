package net.helpdeskhelper.helpdeskhelper.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;



import net.helpdeskhelper.helpdeskhelper.persistence.model.UserRoleModel;



public interface UserRoleRepository extends JpaRepository<UserRoleModel, Integer> {
	
	List<UserRoleModel> findByUser(String user);
}

