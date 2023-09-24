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

import com.cwc.model.Product;
import com.cwc.services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/product")
@Slf4j
@Tag(name = "Product", description = "Manage Product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Operation(summary = "Create a new product")
    @ApiResponses({
        @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = Product.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "500", content = {
            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
        }),
    })
	@PostMapping("/")
	public ResponseEntity<Product> addNewProduct(@RequestBody Product product) {
		Product savedProduct = this.productService.addProduct(product);
		log.info("product added!!!!!!!!");
		return new ResponseEntity<Product>(savedProduct, HttpStatus.OK);
	}

	
	@Operation(summary = "Update product information")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Product.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "500", content = {
            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "404", content = {
            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
        }),
    })
	@PutMapping("/{productId}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product,
			@PathVariable("productId") int productId) {
		Product updatedProduct = this.productService.updateProduct(product, productId);
		return new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);
	}

	
	@Operation(summary = "Retrieve a product")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Product.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "500", content = {
            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "404", content = {
            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
        })
    })
	@GetMapping("/{productId}")
	public ResponseEntity<Product> getSingleProduct(@PathVariable("productId") int productId) {
		Product singleProduct = this.productService.singleProduct(productId);
		return new ResponseEntity<Product>(singleProduct, HttpStatus.OK);
	}

	 @Operation(summary = "Retrieve all Products")
	    @ApiResponses({
	        @ApiResponse(responseCode = "200", content = {
	            @Content(schema = @Schema(implementation = Product[].class), mediaType = "application/json")
	        }),
	        @ApiResponse(responseCode = "500", content = {
	            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
	        })
	    })
	@GetMapping("/")
	public ResponseEntity<List<Product>> getAllProduct() {
		List<Product> allProduct = this.productService.retriveAllProducts();

		return new ResponseEntity<List<Product>>(allProduct,HttpStatus.OK);
	}

	 @Operation(summary = "Delete a product")
	    @ApiResponses({
	        @ApiResponse(responseCode = "206", content = {
	            @Content(schema = @Schema(), mediaType = "application/json")
	        }),
	        @ApiResponse(responseCode = "500", content = {
	            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
	        })
	    })
	@DeleteMapping("/{productId}")
	public ResponseEntity<Void> deleteProduct(@PathVariable("productId") int productId) {
		this.productService.deleteProduct(productId);
		return ResponseEntity.noContent().build();
	}
	
	 
	 @Operation(summary = "Retrieve all Products by category id")
	    @ApiResponses({
	        @ApiResponse(responseCode = "200", content = {
	            @Content(schema = @Schema(implementation = Product[].class), mediaType = "application/json")
	        }),
	        @ApiResponse(responseCode = "500", content = {
	            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
	        })
	    })
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<Product>> productListByCategory(@PathVariable("categoryId") int categoryId){
		List<Product> productsByCategory = productService.getProductByCategory(categoryId);
		return new ResponseEntity<List<Product>>(productsByCategory,HttpStatus.OK);
	}

}
