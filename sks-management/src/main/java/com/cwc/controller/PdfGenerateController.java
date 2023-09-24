package com.cwc.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cwc.model.Category;
import com.cwc.model.Product;
import com.cwc.services.CategoryService;
import com.cwc.services.ProductService;
import com.cwc.utils.PdfGeneratorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/pdf")
@Slf4j
@Tag(name = "PDF Generate", description = "Manage pdf File")
public class PdfGenerateController {

	@Autowired
	private PdfGeneratorService pdfGeneratorService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	
	
	@Operation(summary = "Retrieve all categories and generate pdf")
    @ApiResponses({
        @ApiResponse(responseCode = "500", content = {
            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
        })
    })
	//Generate pdf using Entity
	@GetMapping("/show")
	public ResponseEntity<byte[]> generatePdf(@RequestParam String entityType) {

		try {
			List<Map<String, String>> data = getDataForEntityType(entityType);
			byte[] pdfBytes = pdfGeneratorService.generatePdf(data, entityType);

			// Set the response headers
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_PDF);
			headers.setContentDispositionFormData("attachment", entityType + ".pdf");

			return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Operation(summary = "Retrieve a category from product and generate pdf")
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
	//Generate pdf using Id
	@GetMapping("/product/category/{categoryId}")
	public ResponseEntity<byte[]> generateUsingIdPdf(@RequestParam String entityType,
			@PathVariable("categoryId") int categoryId) {

		try {
			List<Map<String, String>> data = getDataForEntityTypeById(entityType, categoryId);
			byte[] pdfBytes = pdfGeneratorService.generatePdf(data, entityType);

			// Set the response headers
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_PDF);
			headers.setContentDispositionFormData("attachment", entityType + ".pdf");

			return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//pdf generate By Id
	private List<Map<String, String>> getDataForEntityTypeById(String entityType, int categoryId) {
		List<Map<String, String>> data = new ArrayList<>();
		if("product_category".equals(entityType)){
		log.info("product category pdf file is generating ");
		List<Product> productCategoryList = this.productService.getProductByCategory(categoryId);
		// Convert product data to a list of maps
			for (Product pcategory : productCategoryList) {
				Map<String, String> productMap = new LinkedHashMap<>();
				productMap.put("ID", String.valueOf(pcategory.getProductId()));
				productMap.put("Name", pcategory.getProductName());
				productMap.put("Category", String.valueOf(pcategory.getCategory().getCategoryName()));
				productMap.put("Quantity", String.valueOf(pcategory.getQuantity()));
				productMap.put("Price Unit", String.valueOf(pcategory.getPricePerUnit()));
				productMap.put("Condition", String.valueOf(pcategory.getProductCondition()));
				productMap.put("Status", String.valueOf(pcategory.getProductStatus()));
				data.add(productMap);
			}
		}
		return data;
	}
	
	//pdf generate with entity name
	private List<Map<String, String>> getDataForEntityType(String entityType) {

		List<Map<String, String>> data = new ArrayList<>();

		if ("product".equals(entityType)) {
			log.info("product pdf file is generating ");
			List<Product> productList = this.productService.retriveAllProducts();
			// Convert product data to a list of maps
			for (Product product : productList) {
				Map<String, String> productMap = new LinkedHashMap<>();
				productMap.put("ID", String.valueOf(product.getProductId()));
				productMap.put("Name", product.getProductName());
				productMap.put("Category", String.valueOf(product.getCategory().getCategoryName()));
				productMap.put("Quantity", String.valueOf(product.getQuantity()));
				productMap.put("Price Unit", String.valueOf(product.getPricePerUnit()));
				productMap.put("Condition", String.valueOf(product.getProductCondition()));
				productMap.put("Status", String.valueOf(product.getProductStatus()));
				data.add(productMap);
			}

		} else if ("category".equals(entityType)) {
			log.info("category pdf file is generating ");
			List<Category> generatePdfData = this.categoryService.generatePdfData();
			for (Category category : generatePdfData) {
				Map<String, String> categoryMap = new LinkedHashMap<>();
				categoryMap.put("ID", String.valueOf(category.getCategoryId()));
				categoryMap.put("Name", category.getCategoryName());
				categoryMap.put("Code", category.getCategoryCode());
				categoryMap.put("Status", String.valueOf(category.getStatus()));
				data.add(categoryMap);
			}
		}

		return data;
	}
}
