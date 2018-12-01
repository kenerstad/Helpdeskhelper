package net.helpdeskhelper.helpdeskhelper.persistence.dao;

import org.springframework.data.repository.CrudRepository;

import net.helpdeskhelper.helpdeskhelper.persistence.model.RoleModel;



public interface RoleRepository extends CrudRepository<RoleModel, Integer> {

}
