package com.cwc.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.cwc.utils.GenerateExcel;
import com.cwc.utils.GeneratePdf;
import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/category")
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
	
	@PutMapping(value = "/{catId}")
	public ResponseEntity<Category> saveCategory(@RequestBody Category productCategory,@PathVariable("catId") int catId){
		Category updatedProductCategory = this.productCategoryService.updateProductCategory(productCategory,catId);
		return new ResponseEntity<Category>(updatedProductCategory,HttpStatus.OK);
	}
	
	@GetMapping(value = "/{catId}")// , consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.ALL_VALUE)
	public ResponseEntity<Category> getSingleCategory(@PathVariable("catId") int catId){
		Category singleProductCategory = this.productCategoryService.getSingleProductCategory(catId);
		return new ResponseEntity<Category>(singleProductCategory,HttpStatus.OK);
	}
	
	@GetMapping(value = "/all")// , consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.ALL_VALUE)
	public ResponseEntity<List<Category>> getAllCategory(){
		List<Category> allProductCategory = this.productCategoryService.getAllProductCategory();
		log.info("data from backend: {}", allProductCategory.toString());
		return new ResponseEntity<List<Category>>(allProductCategory,HttpStatus.OK);
	}
	
	@DeleteMapping("/{catId}")
	public void  deleteProductCategory(@PathVariable("catId") int catId){
		 this.productCategoryService.deleteProductCategory(catId);
	}
	
	
	
	@GetMapping("/pdf")
	public ResponseEntity<byte[]> exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
	    response.setContentType("application/pdf");
	    DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	    String currentDateTime = dateFormatter.format(new Date());

	    String headerKey = "Content-Disposition";
	    String headerValue = "inline; filename=category" + currentDateTime + ".pdf";
	    response.setHeader(headerKey, headerValue);

	    List<Category> categories = productCategoryService.generatePdfData();

	    GeneratePdf exporter = new GeneratePdf(categories);
	    byte[] pdfBytes = exporter.export(); // Change this method to return byte[] or a way to obtain the byte array

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_PDF);
	    headers.setContentDispositionFormData("filename", "category.pdf");

	    return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
	}
	
	@GetMapping("/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=category" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Category> phoneBookList = productCategoryService.generateExcelData();
        GenerateExcel excelExporter = new GenerateExcel(phoneBookList);
        excelExporter.export(response);
    }
	
	
//	@GetMapping("/pdf")
//    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
//        response.setContentType("application/pdf");
//        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
//        String currentDateTime = dateFormatter.format(new Date());
//
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=category" + currentDateTime + ".pdf";
//        response.setHeader(headerKey, headerValue);
//
//        List<Category> listCategoies = productCategoryService.generatePdfData();
//
//        GeneratePdf exporter = new GeneratePdf(listCategoies);
//        exporter.export(response);
//
//    }

	
	
	
//	@GetMapping("/excel")
//    public ResponseEntity<byte[]> generateAndDownloadExcel(HttpServletResponse response) throws IOException {
//        response.setContentType("application/octet-stream");
//        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
//        String currentDateTime = dateFormatter.format(new Date());
//
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=category" + currentDateTime + ".xlsx";
//        response.setHeader(headerKey, headerValue);
//
//        List<Category> phoneBookList = productCategoryService.generateExcelData();
//        GenerateExcel excelExporter = new GenerateExcel(phoneBookList);
//
//        // Generate the Excel file and obtain the byte array
//        byte[] excelBytes = excelExporter.export();
//        // Create HttpHeaders to set the response content type and disposition
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(org.springframework.http.MediaType.APPLICATION_OCTET_STREAM);
//        headers.setContentDispositionFormData("attachment", "category" + currentDateTime + ".xlsx");
//
//        // Create a ResponseEntity with the byte array, headers, and status OK
//        return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
//    }

	
	


}
