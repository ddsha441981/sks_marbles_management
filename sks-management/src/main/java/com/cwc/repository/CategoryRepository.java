package com.cwc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cwc.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
