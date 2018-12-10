package net.helpdeskhelper.helpdeskhelper.persistence.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import net.helpdeskhelper.helpdeskhelper.persistence.model.RoleModel;


@Transactional
public interface RoleRepository extends CrudRepository<RoleModel, Integer> {

	public RoleModel findByRoleName(String name);
}
