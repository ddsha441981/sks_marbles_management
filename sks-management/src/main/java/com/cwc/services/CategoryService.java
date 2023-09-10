package com.cwc.services;

import java.util.List;

import com.cwc.model.Category;

public interface CategoryService {

	//add Category
		public Category addProductCategory(Category productCategory);
		//update Category
		public Category updateProductCategory(Category productCategory,int catId);
		//get Single Category
		public Category getSingleProductCategory(int catId);
		//get All Category
		public  List<Category> getAllProductCategory();
		//delete Category
		public void deleteProductCategory(int catId);
}
