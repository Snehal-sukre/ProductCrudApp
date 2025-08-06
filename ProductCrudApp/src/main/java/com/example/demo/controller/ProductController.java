package com.example.demo.controller;

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

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	ProductService prodService;
	
	@PostMapping
	public Product saveProduct(@RequestBody Product product)
	{
		return prodService.saveProduct(product);
	}
	
	@GetMapping
	public Page<Product> getAllProducts(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="5") int size)
	{
		return prodService.getAllProducts(page, size);
	}
	
	@GetMapping("/{id}")
	public Product getProductById(@PathVariable int id)
	{
		return prodService.getProductById(id).orElse(null);
	}
	
	@PutMapping("/{id}")
	public Product updateProductById(@PathVariable int id, @RequestBody Product prod)
	{
		return prodService.updateProductById(id, prod);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCategoryById(@PathVariable int id)
	{
		prodService.deleteProductbyId(id);
	}
}
