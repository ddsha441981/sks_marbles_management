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

import com.cwc.model.Supplier;
import com.cwc.services.SupplierService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/supplier")
@Slf4j
@Tag(name = "Supplier", description = "Manage Supplier")
public class SupplierController {
	
	@Autowired
	private SupplierService supplierService;
	
	
	@Operation(summary = "Create a new supplier")
    @ApiResponses({
        @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = Supplier.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "500", content = {
            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
        }),
    })
	@PostMapping("/")
	public ResponseEntity<Supplier> addSupplier(@RequestBody Supplier supplier){
		Supplier addedSupplier = this.supplierService.addSupplier(supplier);
		return new ResponseEntity<Supplier>(addedSupplier,HttpStatus.OK);
	}
	
	@Operation(summary = "Update supplier information")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Supplier.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "500", content = {
            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "404", content = {
            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
        }),
    })
	@PutMapping("/{supplierId}")
	public ResponseEntity<Supplier> updateSupplier(@RequestBody Supplier supplier, @PathVariable("supplierId") int supplierId){
		Supplier updatedSupplier = this.supplierService.updateSupplier(supplier, supplierId);
		return new ResponseEntity<Supplier>(updatedSupplier,HttpStatus.OK);
	}
	
	@Operation(summary = "Retrieve a supplier")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Supplier.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "500", content = {
            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "404", content = {
            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
        })
    })
	@GetMapping("/{supplierId}")
	public ResponseEntity<Supplier> getsingleSupplier(@PathVariable("supplierId") int supplierId){
		Supplier singleSupplier = this.supplierService.singleSupplier(supplierId);
		return new ResponseEntity<Supplier>(singleSupplier,HttpStatus.OK);
	}
	
	
	@Operation(summary = "Retrieve all suppliers")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Supplier[].class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "500", content = {
            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
        })
    })
	@GetMapping("/")
	public ResponseEntity<List<Supplier>> getAllSuppliers(){
		List<Supplier> supplierList = this.supplierService.getSupplierList();
		return new ResponseEntity<List<Supplier>>(supplierList,HttpStatus.OK);
	}
	
	 @Operation(summary = "Delete a supplier")
	    @ApiResponses({
	        @ApiResponse(responseCode = "206", content = {
	            @Content(schema = @Schema(), mediaType = "application/json")
	        }),
	        @ApiResponse(responseCode = "500", content = {
	            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
	        })
	    })
	@DeleteMapping("/{supplierId}")
	public ResponseEntity<Void> deleteSupplier(@PathVariable("supplierId") int supplierId){
		this.supplierService.deleteSupplier(supplierId);
		return ResponseEntity.noContent().build();
		
	}

}
