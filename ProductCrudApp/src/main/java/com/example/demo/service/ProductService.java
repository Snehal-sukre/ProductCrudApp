package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;

@Service("prodService")
public class ProductService {
	
	@Autowired
	ProductRepository prodRepo;
	
	@Autowired
	CategoryRepository catRepo;
	
	public Product saveProduct(Product prod)
	{
		if(prod.getCategory()!=null)
		{
			Category cat=catRepo.findById(prod.getCategory().getId()).orElse(null);
			prod.setCategory(cat);
		}
		return prodRepo.save(prod);
	}
	
	public Page<Product> getAllProducts(int page, int size)
	{
		Pageable pageable=PageRequest.of(page, size);
		return prodRepo.findAll(pageable);
	}
	
	public Optional<Product> getProductById(int id)
	{
		return prodRepo.findById(id);
	}
	
	public Product updateProductById(int id, Product updProd)
	{
		return prodRepo.findById(id).map(existing->
		{
			existing.setName(updProd.getName());
			existing.setPrice(updProd.getPrice());
			if(updProd.getCategory()!=null)
			{
				Category cat=catRepo.findById(updProd.getCategory().getId()).orElse(null);
				existing.setCategory(cat);
			}
			return prodRepo.save(existing);
		}).orElse(null);
	}
	
	public void deleteProductbyId(int id)
	{
		prodRepo.deleteById(id);
	}
}
