package com.cwc.controller;

import java.io.IOException;
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
import com.cwc.utils.ExcelGeneratorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/excel")
@Tag(name = "Excel Generate", description = "Manage Excel File")
public class ExcelGeneratorController {

	@Autowired
	private ExcelGeneratorService excelGeneratorService;

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;


	 @Operation(summary = "Retrieve all categories and export data in excel")
	    @ApiResponses({
	        @ApiResponse(responseCode = "500", content = {
	            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
	        })
	    })
	@GetMapping("/data")
	public ResponseEntity<byte[]> exportDataToExcel(@RequestParam String entityType) {

		try {
			List<Map<String, String>> data = getDataForEntityType(entityType);

			byte[] excelBytes = excelGeneratorService.exportDataToExcel(data, entityType);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("attachment", entityType + ".xlsx");

			return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
		} catch (IOException e) {
			// Handle the error here
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Operation(summary = "Retrieve a category and export data in excel")
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
	@GetMapping("/product/category/{categoryId}")
	public ResponseEntity<byte[]> exportDataToExcelById(@PathVariable("categoryId") int categoryId,
			@RequestParam String entityType) {

		try {
			List<Map<String, String>> data = getDataForEntityType(categoryId, entityType);

			byte[] excelBytes = excelGeneratorService.exportDataToExcel(data, entityType);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("attachment", entityType + ".xlsx");

			return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
		} catch (IOException e) {
			// Handle the error here
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// export excel with categoryId
	private List<Map<String, String>> getDataForEntityType(int categoryId, String entityType) {
		List<Map<String, String>> data = new ArrayList<>();

		if ("product_category".equals(entityType)) {
			log.info("product category excel file is generating ");
			List<Product> productList = this.productService.getProductByCategory(categoryId);
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
		}
		return data;
	}

	// export excel with any entity
	private List<Map<String, String>> getDataForEntityType(String entityType) {

		List<Map<String, String>> data = new ArrayList<>();

		if ("product".equals(entityType)) {
			log.info("product excel file is generating ");
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
			log.info("category excel file is generating ");
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
