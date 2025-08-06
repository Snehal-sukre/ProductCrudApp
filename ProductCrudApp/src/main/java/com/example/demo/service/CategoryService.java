package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;

@Service("catService")
public class CategoryService {

	@Autowired
	CategoryRepository catRepo;
	
	public Category saveCategory(Category category)
	{
		return catRepo.save(category);
	}
	
	public Page<Category> getAllCategories(int page, int size)
	{
		Pageable pageable=PageRequest.of(page, size);
		return catRepo.findAll(pageable);
	}
	
	public Optional<Category> getCategoryById(int id)
	{
		return catRepo.findById(id);
	}
	
	public Category updateCategoryById(int id, Category updCat)
	{
		return catRepo.findById(id).map(existing->
		{
			existing.setName(updCat.getName());
			return catRepo.save(existing);
		}).orElse(null);
	}
	
	public void deleteCategoryById(int id)
	{
		catRepo.deleteById(id);
	}
}
