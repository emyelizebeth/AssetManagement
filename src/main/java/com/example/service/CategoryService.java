package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.AssetCategory;
import com.example.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<AssetCategory> findAllCategory(){
		return categoryRepository.findAll();
	}
	
	public AssetCategory findCategoryById(Long id) {
		AssetCategory category = categoryRepository.findById(id).orElseThrow(()-> new RuntimeException("Category Not found"));
		return category;
	}
	
	public void createCategory(AssetCategory category) {
		categoryRepository.save(category);
	}
	
	public void updateCategory(AssetCategory category) {
		categoryRepository.save(category);
	}

}
