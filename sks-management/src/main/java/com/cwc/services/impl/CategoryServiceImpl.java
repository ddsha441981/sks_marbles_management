package com.cwc.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwc.exception.DuplicateCategoryException;
import com.cwc.exception.ResourceNotFoundException;
import com.cwc.model.Category;
import com.cwc.repository.CategoryRepository;
import com.cwc.services.CategoryService;
import com.cwc.utils.CategoryCode;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category addProductCategory(Category productCategory) {
		String categoryCode = productCategory.getCategoryCode();

        // Check if a category with the same code already exists
        Category existingCategoryCode = categoryRepository.findByCategoryCode(categoryCode);

        if (existingCategoryCode != null)
            throw new DuplicateCategoryException("Category with code " + categoryCode + " already exists.");

        String categoryName = productCategory.getCategoryName();
        String generateCategoryCode = CategoryCode.generateCategoryCode(categoryName);
       
        productCategory.setCategoryCode(generateCategoryCode);
		productCategory.setCategoryImage("default.png");
		Category savedCategory = this.categoryRepository.save(productCategory);
		return savedCategory;
	}

	@Override
	public Category updateProductCategory(Category productCategory, int catId) {
		Category category = this.categoryRepository.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("Resource is not found!!!"));

		category.setCategoryName(productCategory.getCategoryName());
		category.setCategorydescription(productCategory.getCategorydescription());
		category.setStatus(productCategory.getStatus());
		return this.categoryRepository.save(category);
		
	}

	@Override
	public Category getSingleProductCategory(int catId) {
		return this.categoryRepository.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("Resource is not found!!!"));
	}

	@Override
	public List<Category> getAllProductCategory() {
		return this.categoryRepository.findAll();
	}

	@Override
	public void deleteProductCategory(int catId) {
		this.categoryRepository.deleteById(catId);

	}

}
