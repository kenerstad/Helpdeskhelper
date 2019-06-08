package net.helpdeskhelper.helpdeskhelper.service;
// ## Java
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Size;

// ## Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// ## Project
import net.helpdeskhelper.helpdeskhelper.persistence.dao.IssueRepository;
import net.helpdeskhelper.helpdeskhelper.persistence.dao.IssueCategoryRepository;
import net.helpdeskhelper.helpdeskhelper.persistence.dao.QuestionRepository;
import net.helpdeskhelper.helpdeskhelper.persistence.model.Issue;
import net.helpdeskhelper.helpdeskhelper.persistence.model.Question;
import net.helpdeskhelper.helpdeskhelper.persistence.model.IssueCategory;
import net.helpdeskhelper.helpdeskhelper.utils.WebUtils;
import net.helpdeskhelper.helpdeskhelper.web.dto.*;



@Service
@Transactional
public class IssueService implements IIssueService{

	@Autowired
	IssueRepository issueRepo;
	
	@Autowired
	QuestionRepository questionRepo;
	
	@Autowired
	IssueCategoryRepository issueCategoryRepo;
	
	AdminFeedbackDTO feedback;
	
	public IssueService() {
		
		feedback = new AdminFeedbackDTO();
	}
	
	/**
	 * Gets all issue categories from DB & inserts into DTO,
	 * Converts DTO to JSON object and returns this object.
	 */
	@SuppressWarnings("null")
	public String getIssueCategories(){

		List<IssueCategory> issueCategories = (List<IssueCategory>) issueCategoryRepo.findAll();
		
		List<CategoryDTO> categories = new ArrayList<CategoryDTO>();
				
		for(IssueCategory issueCategory : issueCategories) {
			CategoryDTO categoryDTO = new CategoryDTO();
			categoryDTO.setId(issueCategory.getCategoryId());
			categoryDTO.setCategoryName(issueCategory.getCategoryName());
			categories.add(categoryDTO);
		}

		return WebUtils.generateJSON(categories);

		 
	}
	
	/**
	 * Gets all issues associated with category ID.
	 * Returns list of issueDTO's.
	 */
	public String getIssuesByCategory(Long catId){
				
		IssueCategory issueCategory = issueCategoryRepo.findByCategoryId(catId);
		List<Issue> issues = issueCategory.getIssues();
		return WebUtils.generateJSON(generateIssueDTOList(issues));
		
		//return WebUtils.generateJSON(category);
	}
	
	/**
	 * Converts collection of issues to list of issue DTO's & returns it.
	 */
	private List<IssueDTO> generateIssueDTOList(Collection<Issue> issues) {
		
		List<IssueDTO> issueList = new ArrayList<IssueDTO>();
		for (Issue issue : issues) {
			IssueDTO issueDTO = new IssueDTO();
			issueDTO.setId(issue.getIssueId());
			issueDTO.setIssueName(issue.getIssueName());
			issueDTO.setissueDescription(issue.getIssueDescription());
			issueList.add(issueDTO);
		}
		return issueList;
	}
	
	/*
	 * Gets all questions related to an issue by the issue's ID.
	 * Generates DTO's for all questions and inserts them into list.
	 * Converts list to JSON object & returns it.
	 */
	public String getQuestionsByIssue(Long issueId) {
		
		Issue issue = this.issueRepo.findByIssueId(issueId);
		List<QuestionDTO> questions = new ArrayList<QuestionDTO>();
				
		for (Question question : issue.getQuestions()) {	
			QuestionDTO questionDTO = new QuestionDTO();
			questionDTO.setId(question.getQuestionID());
			questionDTO.setQuestionText(question.getQuestion());
			questions.add(questionDTO);	
		}

		return WebUtils.generateJSON(questions);
	}
		
	/*
	 * Public method for adding single new category by name.
	 * Calls private method generateCategory() for impl.
	 * returns JSON object of feedbackDTO.
	 */
	public String addCategory(String category) {
		
		feedback.setId(generateCategory(category));
		return WebUtils.generateJSON(feedback);
	}
	
	/*
	 * Public method for adding single issue to category.
	 * Calls private method generateIssue() for impl.
	 * returns JSON object of feedbackDTO.
	 */
	public String addIssueToCategory(Long catId, String issueName, String issueDescription) {
		
		feedback.setId(generateIssue(catId, issueName, issueDescription));
		return WebUtils.generateJSON(feedback);
	}
	
	/*
	 * Public method for adding single question to issue.
	 * Calls private method generateQuestion() for impl.
	 * Returns JSON object of feedbackDTO.
	 */
	public String addQuestionToIssue(Long issueId, String questionText) {
		
		feedback.setId(generateQuestion(issueId, questionText));
		return WebUtils.generateJSON(feedback);
	}
	
	/*
	 * Public method for generating template categories, issues & questions.
	 */
	public void generateTemplates() {
		
		Long cat1Id = generateCategory("Category1");
		Long cat2Id = generateCategory("Category2");
		Long cat3Id = generateCategory("Category3");
		
		Long issue1Id = generateIssue(cat1Id, "issue1", "issue1 description goes here");
		Long issue2Id = generateIssue(cat1Id, "issue2", "issue2 description goes here");
		Long issue3Id = generateIssue(cat1Id, "issue3", "issue3 description goes here");
		
		generateIssue(cat2Id, "issue4", "issue1 description goes here");
		generateIssue(cat2Id, "issue5", "issue2 description goes here");
		generateIssue(cat2Id, "issue6", "issue3 description goes here");
		
		generateQuestion(issue1Id, "this is question 1");
		generateQuestion(issue1Id, "this is question 2");
		
		generateQuestion(issue2Id, "this is question 1");
		generateQuestion(issue2Id, "this is question 2");
		//generateQuestion("this is question 3", "issue1");
	}
	
	/*
	 * Creates  new IssueCategory with submitted name & saves to repository.
	 */
	public Long generateCategory (String categoryName) {
		
		IssueCategory category = new IssueCategory();
		category.setCategoryName(categoryName);
		try {
			category = issueCategoryRepo.save(category);				
		}				
		catch (Exception e) {
			feedback.setMsg("Error generating category: " +e);
			return (long) -1;
		}
		feedback.setMsg("Successfully generated category: ");
		return category.getCategoryId();
	}
	
	/*
	 * Updates name of category.
	 * Retrieves category from repository by id & updates name,
	 * saves changes to repository.
	 * Returns feedback for namechange.
	 */
	public String updateCategory (Long id, String catName, String catNameChange) {
		
		IssueCategory catModel = issueCategoryRepo.findByCategoryId(id);
		catModel.setCategoryName(catNameChange);
		
		feedback.setName(catName);
		feedback.setNameChange(catNameChange);

		try {
			issueCategoryRepo.save(catModel);				
		}				
		catch (Exception e) {
			feedback.setMsg("Error: " +e);
			return WebUtils.generateJSON(feedback);
		}		
		feedback.setMsg("Successfully updated category: " +catName +" to " +catNameChange);
		return WebUtils.generateJSON(feedback);
	}
	
	/*
	 * Deletes category by id.
	 * Retrieves category from repository by id,
	 * deletes retrieved category from repository/DB.
	 * Returns feedback on success/failure.
	 */
	public String deleteCategory(Long id) {
		
		IssueCategory catModel = issueCategoryRepo.findByCategoryId(id);
		
		feedback.setName(catModel.getCategoryName());
			
		try {
			issueCategoryRepo.delete(catModel);			
		}				
		catch (Exception e) {
			feedback.setMsg("Error: " +e);
			return WebUtils.generateJSON(feedback);
		}
		
		feedback.setMsg("Successfully deleted");
		return WebUtils.generateJSON(feedback);
	}	
	
	/*
	 * Creates new issue with submitted name & description.
	 * Finds category with submitted id, creates new issue,
	 * sets issue name & description, associates issue with specified category.
	 * Returns feedback on success/failure.
	 */
	private Long generateIssue (Long categoryId, String issueName, String issueDescription) {
		
		IssueCategory category = issueCategoryRepo.findByCategoryId(categoryId);
		
		Issue issue = new Issue();
		issue.setIssueName(issueName);
		issue.setIssueDescription(issueDescription);
		issue.setIssueCategory(category);
		
		List<Question> questions = new ArrayList<Question>();
		issue.setQuestions(questions);
		
		try {
			issue = issueRepo.save(issue);			
		}				
		catch (Exception e) {
			feedback.setMsg("Error generating issue: " +e);
			return (long) -1;
		}

		feedback.setMsg("Successfully added issue to category: " +category.getCategoryName());
		return issue.getIssueId();
	}
	
	/*
	 * Updates name & description of issue.
	 * Retrieves issue from repository by id & updates name & description,
	 * saves changes to repository,
	 * returns feedback for namechange.
	 */
	public String updateIssue(Long id, String issueName, String issueDescription) {
		
		Issue issue = issueRepo.findByIssueId(id);
		issue.setIssueName(issueName);
		issue.setIssueDescription(issueDescription);
		
		try {
			issue = issueRepo.save(issue);		
		}				
		catch (Exception e) {
			feedback.setMsg("Error updating issue: " +e);
			 WebUtils.generateJSON(feedback);
		}
		
		feedback.setMsg("Successfully updated issue: ");
		return WebUtils.generateJSON(feedback);
	}
	
	/*
	 * Deletes issue by id.
	 * Retrieves issue from repository by id,
	 * deletes retrieved issue from repository,
	 * returns feedback on success/failure.
	 */
	public String deleteIssue(Long id) {
		
		Issue issue = issueRepo.findByIssueId(id);
		
		feedback.setName(issue.getIssueName());
		
		try {
			issueRepo.delete(issue);			
		}				
		catch (Exception e) {
			feedback.setMsg("Error: " +e);
			return WebUtils.generateJSON(feedback);
		}
		
		feedback.setMsg("Successfully deleted");
		return WebUtils.generateJSON(feedback);
	}
	
	/*
	 * Creates new question with specified text.
	 * Retrieves issue by specified id from repository,
	 * creates new question & sets text & associates question with retrieved issue,
	 * returns feedback on success/failure.
	 */
	private Long generateQuestion (Long issueId, String questionText) {
		
		Issue issue = issueRepo.findByIssueId(issueId);
						
		Question question = new Question();
		question.setIssue(issue);
		question.setQuestion(questionText);	
		issue.addQuestion(question);
		
		try {
			question = questionRepo.save(question);		
		}				
		catch (Exception e) {
			feedback.setMsg("Error generating question: " +e);
			return (long) -1;
		}
		issueRepo.save(issue);
		
		
		return question.getQuestionID();
	}
	
	/*
	 * Updates question text.
	 * Retrieves question from repository by id & updates text,
	 * saves changes to repository,
	 * returns feedback on success/failure.
	 */
	public String updateQuestion(Long id, String questionChange) {
		
		Question question = questionRepo.findByQuestionId(id);		
		question.setQuestion(questionChange);
		
		try {
			question = questionRepo.save(question);		
		}				
		catch (Exception e) {
			feedback.setMsg("Error updating question: " +e);
			 WebUtils.generateJSON(feedback);
		}
		
		feedback.setMsg("Successfully updated question: ");
		return WebUtils.generateJSON(feedback);
	}
	
	/*
	 * Deletes question by id.
	 * Retrieves question from repository by id,
	 * deletes retrieved question from repository,
	 * returns feedback on success/failure.
	 */
	public String deleteQuestion(Long id) {
		
		Question question = questionRepo.findByQuestionId(id);
		
		try {
			questionRepo.delete(question);			
		}				
		catch (Exception e) {
			feedback.setMsg("Error: " +e);
			return WebUtils.generateJSON(feedback);
		}
		
		feedback.setMsg("Successfully deleted");
		return WebUtils.generateJSON(feedback);
		
	}
		
}
