package net.helpdeskhelper.helpdeskhelper.persistence.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.helpdeskhelper.helpdeskhelper.persistence.model.IssueCategory;
import net.helpdeskhelper.helpdeskhelper.persistence.model.Question;

public interface QuestionRepository extends CrudRepository<Question, Integer>{

	public Question findByQuestionId(Long id);
}
