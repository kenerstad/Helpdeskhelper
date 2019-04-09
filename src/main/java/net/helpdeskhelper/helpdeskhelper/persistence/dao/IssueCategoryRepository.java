package net.helpdeskhelper.helpdeskhelper.persistence.dao;

import net.helpdeskhelper.helpdeskhelper.persistence.model.IssueCategory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IssueCategoryRepository extends CrudRepository<IssueCategory, Integer>{

	public IssueCategory findByCategoryName(String name);
	public IssueCategory findByCategoryId(Long id);
}
