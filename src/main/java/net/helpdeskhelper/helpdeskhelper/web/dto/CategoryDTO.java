package net.helpdeskhelper.helpdeskhelper.web.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CategoryDTO {
	
	private String categoryName;
	private String categoryDescription;
	private Long id;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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

}
