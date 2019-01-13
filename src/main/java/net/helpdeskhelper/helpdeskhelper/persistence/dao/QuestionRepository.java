package net.helpdeskhelper.helpdeskhelper.persistence.dao;

import org.springframework.data.repository.CrudRepository;

import net.helpdeskhelper.helpdeskhelper.persistence.model.Question;

public interface QuestionRepository extends CrudRepository<Question, Integer>{

}
