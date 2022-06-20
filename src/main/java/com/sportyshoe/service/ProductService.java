package com.sportyshoe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoe.DAO.ProductsDAO;
import com.sportyshoe.model.Products;

@Service
public class ProductService {
	
	@Autowired
	private ProductsDAO productDAO;
	
	
	public void save(Products product) {
		productDAO.save(product);
	}
	
	public List<Products> getProducts(){
		
		return productDAO.findAll();
	
	}
	
	public Products editProduct(long id) {
		
		return productDAO.getProductById(id);
			
	}
	
	 public int deleteProduct(Long id) {
		 
		 return productDAO.deleteById(id);
	 }
	 
	 
	 public void updateProduct(Products product) {
		 
		 productDAO.updateProduct(product);
	 }

}
