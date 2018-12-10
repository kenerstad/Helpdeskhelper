package net.helpdeskhelper.helpdeskhelper.persistence.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import net.helpdeskhelper.helpdeskhelper.persistence.model.UserModel;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete


@Transactional
public interface UserRepository extends CrudRepository<UserModel, Integer> {

	UserModel findByEmail (String email);
	UserModel findByUserName (String userName);
	
}
