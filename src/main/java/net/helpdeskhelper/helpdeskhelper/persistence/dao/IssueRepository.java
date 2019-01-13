package net.helpdeskhelper.helpdeskhelper.persistence.dao;

import org.springframework.data.repository.CrudRepository;

import net.helpdeskhelper.helpdeskhelper.persistence.model.Issue;

public interface IssueRepository extends CrudRepository<Issue, Integer>{

	public Issue findByIssueName(String name);
	public Issue findByIssueCategory(String category);
}
