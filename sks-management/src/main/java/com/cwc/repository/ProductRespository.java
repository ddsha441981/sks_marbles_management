package com.cwc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cwc.model.Product;

@Repository
public interface ProductRespository extends JpaRepository<Product, Integer> {

	public List<Product> findByCategoryCategoryId(int categoryId);

}
