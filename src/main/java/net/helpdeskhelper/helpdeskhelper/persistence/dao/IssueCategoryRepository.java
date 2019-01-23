package net.helpdeskhelper.helpdeskhelper.persistence.dao;

import net.helpdeskhelper.helpdeskhelper.persistence.model.Issue;
import net.helpdeskhelper.helpdeskhelper.persistence.model.IssueCategory;

import org.springframework.data.repository.CrudRepository;

public interface IssueCategoryRepository extends CrudRepository<IssueCategory, Integer>{

	public IssueCategory findByCategoryName(String name);
}
