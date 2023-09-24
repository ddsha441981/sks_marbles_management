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

import com.cwc.model.Purchase;
import com.cwc.services.PurchaseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/purchase")
@Slf4j
@Tag(name = "Purchase", description = "Manage Product Purchase")
public class PurchaseController {

	@Autowired
	private PurchaseService purchaseService;
	
	
	@Operation(summary = "Create a new product purchase")
    @ApiResponses({
        @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = Purchase.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "500", content = {
            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
        }),
    })
	@PostMapping("/")
	public ResponseEntity<Purchase> addPurchaseProduct(@RequestBody Purchase purchase){
		Purchase addedPurchase = this.purchaseService.addPurchase(purchase);
		log.info("Product Purchase added with Id {}",purchase.getPurchaseId());
		return new ResponseEntity<Purchase>(addedPurchase,HttpStatus.OK);
	}
	
	@Operation(summary = "Update purchase information")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Purchase.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "500", content = {
            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "404", content = {
            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
        }),
    })
	@PutMapping("/{purchaseId}")
	public ResponseEntity<Purchase> updatePurchaseProduct(@RequestBody Purchase purchase,@PathVariable("purchaseId") int purchaseId){
		Purchase updatedPurchase = this.purchaseService.updatePurchase(purchase, purchaseId);
		return new ResponseEntity<Purchase>(updatedPurchase,HttpStatus.OK);
	}
	
	@Operation(summary = "Retrieve a Purchase Product")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Purchase.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "500", content = {
            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "404", content = {
            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
        })
    })
	@GetMapping("/{purchaseId}")
	public ResponseEntity<Purchase> singlePurchaseProduct(@PathVariable("purchaseId") int purchaseId){
		Purchase singlePurchase = this.purchaseService.singlePurchase(purchaseId);
		return new ResponseEntity<Purchase>(singlePurchase,HttpStatus.OK);
	}
	
	@Operation(summary = "Retrieve all Purchases of Product")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Purchase[].class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "500", content = {
            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
        })
    })
	@GetMapping("/")
	public ResponseEntity<List<Purchase>> getPurchaseProductList(){
		List<Purchase> purchaseList = this.purchaseService.getPurchaseList();
		return new ResponseEntity<List<Purchase>>(purchaseList,HttpStatus.OK);
	}
	
	
	@Operation(summary = "Delete a purchase product")
    @ApiResponses({
        @ApiResponse(responseCode = "206", content = {
            @Content(schema = @Schema(), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "500", content = {
            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
        })
    })
	@DeleteMapping("/{purchaseId}")
	public ResponseEntity<Void> deletePurchaseProduct(@PathVariable("purchaseId") int purchaseId){
		this.purchaseService.deletePurchase(purchaseId);
		return ResponseEntity.noContent().build();
	}
}
