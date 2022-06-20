package com.sportyshoe.DAO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.sportyshoe.model.Products;

public interface ProductsRepository {

	    int count();

	    int save(Products product);

	    int deleteById(Long id);

	    List<Products> findAll();

	    List<Products> findByNameAndPrice(String name, BigDecimal price);

	    Optional<Products> findById(Long id);

	    String getNameById(Long id);
	    
	    Products getProductById(long id);
	    
	    void updateProduct(Products product);
}
