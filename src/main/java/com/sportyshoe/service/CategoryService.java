package com.sportyshoe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoe.DAO.CategoryDAO;
import com.sportyshoe.model.Category;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	public void saveCategory(Category category) {
		categoryDAO.save(category);
	}
	
	public List<Category> getCategory(){
		
		return categoryDAO.findAllCat();		
			}

}
