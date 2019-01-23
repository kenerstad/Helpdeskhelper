package net.helpdeskhelper.helpdeskhelper.persistence.model;

import java.util.Set;

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
	
	@NotNull
	@Size(max = 150)
	private String categoryDescription;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "issueCategory")
	private Set<Issue> issues;
	
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

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public Set<Issue> getIssues() {
		return issues;
	}

	public void setIssues(Set<Issue> issues) {
		this.issues = issues;
	}
}
