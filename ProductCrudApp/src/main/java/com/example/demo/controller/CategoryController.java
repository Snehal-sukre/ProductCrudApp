package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	CategoryService catService;
	
	@PostMapping
	public String saveCategory(@RequestBody Category category)
	{
		Category cat=catService.saveCategory(category);
		return cat!=null? "Category Saved" : "Category Not Saved";
	}
	
	@GetMapping
	public Page<Category> getAllCategories(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="5") int size)
	{
		return catService.getAllCategories(page, size);
	}
	
	@GetMapping("/{id}")
	public Category getCategoryById(@PathVariable int id)
	{
		return catService.getCategoryById(id).orElse(null);
	}
	
	@PutMapping("/{id}")
	public Category updateCategoryById(@PathVariable int id, @RequestBody Category cat)
	{
		return catService.updateCategoryById(id, cat);
	}
	
	@DeleteMapping("{id}")
	public void deleteCategoryById(@PathVariable int id)
	{
		catService.deleteCategoryById(id);
	}
}
