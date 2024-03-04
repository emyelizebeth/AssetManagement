package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entity.AssetCategory;
import com.example.service.CategoryService;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/categories")
	public String findAllCategories(Model model){
		List<AssetCategory> categories = categoryService.findAllCategory();
		model.addAttribute("categories", categories);
		return "categoryPage";
	}
	
	
	
	@GetMapping("/add-category")
	public String addCategory(Model model) {
	    model.addAttribute("category", new AssetCategory()); 
	    return "add-category";
	}

	
	@PostMapping("/save-category")
	public String saveCategory(AssetCategory category,  BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "add-category";
		}
		categoryService.createCategory(category);
		model.addAttribute("categories", categoryService.findAllCategory());
		return "redirect:/categories";
	}
		
		@GetMapping("/update-category/{id}")
		public String updateCategory(@PathVariable Long id, Model model) {
			AssetCategory category = categoryService.findCategoryById(id);
			model.addAttribute("categories", category);
			return "update-category"; 
		}
		
		@PostMapping("/save-update-category/{id}")
		public String saveCategory(@PathVariable Long id, AssetCategory category, BindingResult result, Model model) {
			if(result.hasErrors()) {
				return "update-category";
			}
			categoryService.updateCategory(category);
			model.addAttribute("categories", categoryService.findAllCategory());
			return "redirect:/categories";
			
		}
	
}
