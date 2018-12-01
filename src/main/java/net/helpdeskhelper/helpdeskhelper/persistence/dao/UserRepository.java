package net.helpdeskhelper.helpdeskhelper.persistence.dao;

import org.springframework.data.repository.CrudRepository;

import net.helpdeskhelper.helpdeskhelper.persistence.model.UserModel;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<UserModel, Integer> {

	UserModel findByEmail (String email);
	UserModel findByUser_Name (String user);
	
}
