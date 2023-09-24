package com.cwc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
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
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api/v1/category")
@Slf4j
@Tag(name = "Category", description = "Manage category")
public class CategoryController {
	
	@Autowired
	private CategoryService productCategoryService;
	
	@SuppressWarnings("unused")
	@Autowired
	private ObjectMapper mapper;
	

	@Operation(summary = "Create a new category")
    @ApiResponses({
        @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = Category.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "500", content = {
            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
        }),
    })
	@PostMapping(value = "/")
	public ResponseEntity<Category> saveCategory(
			@Valid 
			@RequestBody Category productCategory){
		log.info("data from fronted app : {} " , productCategory);
				
		Category savedCategory = this.productCategoryService.addProductCategory(productCategory);
		return new ResponseEntity<Category>(savedCategory,HttpStatus.OK);
	}
	
	
	@Operation(summary = "Update category information")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Category.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "500", content = {
            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "404", content = {
            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
        }),
    })
	@PutMapping(value = "/{catId}")
	public ResponseEntity<Category> saveCategory(@RequestBody Category productCategory,@PathVariable("catId") int catId){
		Category updatedProductCategory = this.productCategoryService.updateProductCategory(productCategory,catId);
		return new ResponseEntity<Category>(updatedProductCategory,HttpStatus.OK);
	}
	
	@Operation(summary = "Retrieve a category")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Category.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "500", content = {
            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "404", content = {
            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
        })
    })
	@GetMapping(value = "/{catId}")// , consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.ALL_VALUE)
	public ResponseEntity<Category> getSingleCategory(@PathVariable("catId") int catId){
		Category singleProductCategory = this.productCategoryService.getSingleProductCategory(catId);
		return new ResponseEntity<Category>(singleProductCategory,HttpStatus.OK);
	}
	
	
	 @Operation(summary = "Retrieve all categories")
	    @ApiResponses({
	        @ApiResponse(responseCode = "200", content = {
	            @Content(schema = @Schema(implementation = Category[].class), mediaType = "application/json")
	        }),
	        @ApiResponse(responseCode = "500", content = {
	            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
	        })
	    })
	@GetMapping(value = "/all")// , consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.ALL_VALUE)
	public ResponseEntity<List<Category>> getAllCategory(){
		List<Category> allProductCategory = this.productCategoryService.getAllProductCategory();
		log.info("data from backend: {}", allProductCategory.toString());
		return new ResponseEntity<List<Category>>(allProductCategory,HttpStatus.OK);
	}
	 
	 
	
	 @Operation(summary = "Delete a category")
	    @ApiResponses({
	        @ApiResponse(responseCode = "206", content = {
	            @Content(schema = @Schema(), mediaType = "application/json")
	        }),
	        @ApiResponse(responseCode = "500", content = {
	            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
	        })
	    })
	@DeleteMapping("/{catId}")
	public ResponseEntity<Void>  deleteProductCategory(@PathVariable("catId") int catId){
		 this.productCategoryService.deleteProductCategory(catId);
		return ResponseEntity.noContent().build();
	}

	//Image and JSON data
	
//	@PostMapping(value = "/")//,consumes = MediaType.APPLICATION_JSON_VALUE ,produces=MediaType.ALL_VALUE)
//	public ResponseEntity<Category> saveCategory(
////			@Valid 
//			@RequestParam(value = "productCategory") String productCategory,
//			@RequestParam(value = "file")MultipartFile file
//			) throws IOException{
//		log.info("data from fronted app : {} " , productCategory);
//		
//		System.out.println(file.getResource());
//		
//		//converting String to Json
//		Category productCategories = mapper.readValue(productCategory, Category.class);		
//		
//		Category savedCategory = this.productCategoryService.addProductCategory(productCategories,file);
//		return new ResponseEntity<Category>(savedCategory,HttpStatus.OK);
//	}
}
