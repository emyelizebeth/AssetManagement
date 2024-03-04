package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.entity.Assets;
import com.example.service.AssetService;
import com.example.service.CategoryService;

@Controller
public class AssetController {

	@Autowired
	private AssetService service;

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/")
	public String viewHomePage(Model model) {
		List<Assets> listAssets = service.listAll();
		model.addAttribute("listAssets", listAssets);

		return "indexAdmin";
	}

	// to update an asset
	@GetMapping("/edit/{id}")
	public String updateAsset(@PathVariable Long id, Model model) {
		Assets asset = service.findAssetById(id);
		model.addAttribute("assets", asset);
		model.addAttribute("categories", categoryService.findAllCategory());
		return "update-asset";
	}

	@PostMapping("/save-update/{id}")
	public String saveUpdateAsset(@PathVariable Long id, Assets asset, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "update-asset";
		}
		Assets existingAsset = service.findAssetById(id);
		asset.setCreatedDate(existingAsset.getCreatedDate());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		asset.setUpdatedUser(username);
		asset.setCreatedUser(existingAsset.getCreatedUser());
		service.updateAsset(asset);
		model.addAttribute("assets", service.listAll());
		return "redirect:/";
	}
	
	
	//to create an asset
		@GetMapping("/add-asset")
		public String addAsset(Assets asset, Model model) {
			model.addAttribute("categories", categoryService.findAllCategory());
			return "add-asset";
		}
			
		@PostMapping("/save-asset")
		public String saveUpdateAsset(Assets asset, BindingResult result, Model model ) {
			if(result.hasErrors()) {
				return "add-asset";
			}
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String username = auth.getName();
			asset.setCreatedUser(username);
			service.createAsset(asset);
			model.addAttribute("assets", service.listAll());
			return "redirect:/"; 
		}
		
		//delete asset
		@GetMapping("/remove-asset/{id}")
		public String deleteAsset(@PathVariable Long id, Model model) {
			service.removeAsset(id);
			model.addAttribute("listAssets", service.listAll());
			return "indexAdmin";
		}
	

}
