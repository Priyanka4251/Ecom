package com.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.model.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {
	
	public Product findByProductCategory(String category);
}
