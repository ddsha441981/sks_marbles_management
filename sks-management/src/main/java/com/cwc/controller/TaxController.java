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

import com.cwc.model.Tax;
import com.cwc.services.TaxService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;



@RestController
@RequestMapping("/api/v1/tax")
@Slf4j
@Tag(name = "Tax", description = "Manage Tax")
public class TaxController {

	@Autowired
	private TaxService taxService;
	
	@Operation(summary = "Create a new tax")
    @ApiResponses({
        @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = Tax.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "500", content = {
            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
        }),
    })
	@PostMapping("/")
	public ResponseEntity<Tax> addtax(@RequestBody Tax tax){
		Tax addedTax = this.taxService.addTax(tax);
		return new ResponseEntity<Tax>(addedTax,HttpStatus.OK);
	}
	
	@Operation(summary = "Update Tax information")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Tax.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "500", content = {
            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "404", content = {
            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
        }),
    })
	@PutMapping("/{taxId}")
	public ResponseEntity<Tax> updateTax(@RequestBody Tax tax, @PathVariable("taxId") int taxId){
		Tax updatedtax = this.taxService.updateTax(tax, taxId);
		return new ResponseEntity<Tax>(updatedtax,HttpStatus.OK);
	}
	
	@Operation(summary = "Retrieve a Tax")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Tax.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "500", content = {
            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "404", content = {
            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
        })
    })
	@GetMapping("/{taxId}")
	public ResponseEntity<Tax> getSingleTax(@PathVariable("taxId") int taxId){
		Tax singleTax = this.taxService.singleTax(taxId);
		return new ResponseEntity<Tax>(singleTax,HttpStatus.OK);
	}
	
	
	@Operation(summary = "Retrieve all tax")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Tax[].class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "500", content = {
            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
        })
    })
	@GetMapping("/")
	public ResponseEntity<List<Tax>> getAlltaxs(){
		List<Tax> taxList = this.taxService.getTaxList();
		return new ResponseEntity<List<Tax>>(taxList,HttpStatus.OK);
	}
	
	
	@Operation(summary = "Delete a tax")
    @ApiResponses({
        @ApiResponse(responseCode = "206", content = {
            @Content(schema = @Schema(), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "500", content = {
            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
        })
    })
	@DeleteMapping("/{taxId}")
	public ResponseEntity<Void> deleteTax(@PathVariable("taxId") int taxId){
		this.taxService.deleteTax(taxId);
		return ResponseEntity.noContent().build();
		
	}

}
