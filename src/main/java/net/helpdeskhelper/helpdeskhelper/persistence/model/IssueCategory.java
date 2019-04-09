package net.helpdeskhelper.helpdeskhelper.persistence.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "issuecategory")
public class IssueCategory {

	@Id
    @GeneratedValue
    @NotNull
    private Long categoryId;
	
	@NotNull
	@Size(max = 36)
	private String categoryName;
		
	@OneToMany(
			cascade = CascadeType.REMOVE, 
			orphanRemoval = true, 
			fetch = FetchType.LAZY, 
			mappedBy = "issueCategory")
	private List<Issue> issues;
	
	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<Issue> getIssues() {
		return issues;
	}

	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}
}
