package net.helpdeskhelper.helpdeskhelper.persistence.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import net.helpdeskhelper.helpdeskhelper.persistence.model.Issue;

@Transactional
public interface IssueRepository extends CrudRepository<Issue, Integer>{

	public Issue findByIssueName(String name);
	public Issue findByIssueId(Long id);
	public List<Issue> findAllByIssueCategory(String category);
}
