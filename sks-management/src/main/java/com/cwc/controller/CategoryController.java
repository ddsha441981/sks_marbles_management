package com.cwc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwc.model.Category;
import com.cwc.services.CategoryService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/api/v1/category")// http://localhost:8091/api/v1/category/
@Slf4j
public class CategoryController {
	
	@Autowired
	private CategoryService productCategoryService;
	
	@PostMapping(value = "/")//,consumes = MediaType.APPLICATION_JSON_VALUE ,produces=MediaType.ALL_VALUE)
	public ResponseEntity<Category> saveCategory(@Valid @RequestBody Category productCategory){
		log.info("data from fronted app : " + productCategory.toString());
		Category savedCategory = this.productCategoryService.addProductCategory(productCategory);
		return new ResponseEntity<Category>(savedCategory,HttpStatus.OK);
	}
	
	@PutMapping(value = "/{catId}")// , consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.ALL_VALUE)
	public ResponseEntity<Category> saveCategory(@RequestBody Category productCategory,@PathVariable("catId") int catId){
		Category updatedProductCategory = this.productCategoryService.updateProductCategory(productCategory,catId);
		return new ResponseEntity<Category>(updatedProductCategory,HttpStatus.OK);
	}
	
	@GetMapping(value = "/{catId}")// , consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.ALL_VALUE)
	public ResponseEntity<Category> getSingleCategory(@PathVariable("catId") int catId){
		Category singleProductCategory = this.productCategoryService.getSingleProductCategory(catId);
		return new ResponseEntity<Category>(singleProductCategory,HttpStatus.FOUND);
	}
	
	@GetMapping(value = "/categories")// , consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.ALL_VALUE)
	public ResponseEntity<List<Category>> getAllCategory(){
		List<Category> allProductCategory = this.productCategoryService.getAllProductCategory();
		return new ResponseEntity<List<Category>>(allProductCategory,HttpStatus.FOUND);
	}
	
	@DeleteMapping("/{catId}")
	public void  deleteProductCategory(@PathVariable("catId") int catId){
		 this.productCategoryService.deleteProductCategory(catId);
	}

}
