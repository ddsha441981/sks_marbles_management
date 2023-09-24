package com.cwc.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwc.exception.ResourceNotFoundException;
import com.cwc.model.Purchase;
import com.cwc.repository.PurchaseRepository;
import com.cwc.services.PurchaseService;

@Service
public class PurchaseServiceImpl implements PurchaseService {

	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@Override
	public Purchase addPurchase(Purchase purchase) {
		return this.purchaseRepository.save(purchase);
	}

	@Override
	public Purchase updatePurchase(Purchase purchase, int purchaseId) {
		Purchase purchases = this.purchaseRepository.findById(purchaseId).orElseThrow(()-> new ResourceNotFoundException("Resource not found with this id {}"));
		purchases.setPurchasedDate(purchase.getPurchasedDate());
		purchases.setPurchasedQuantity(purchase.getPurchasedQuantity());
		purchases.setPricePerUnit(purchase.getPricePerUnit());
		purchases.setAmount(purchase.getAmount());
		purchases.setProduct(purchase.getProduct());
		purchases.setTax(purchase.getTax());
		purchases.setUpdatedAt(purchase.getUpdatedAt());
		return this.purchaseRepository.save(purchases);
	}

	@Override
	public Purchase singlePurchase(int purchaseId) {
		return this.purchaseRepository.findById(purchaseId).orElseThrow(()-> new ResourceNotFoundException("Resource not found with this id {}"));
	}

	@Override
	public List<Purchase> getPurchaseList() {
		return this.purchaseRepository.findAll();
	}

	@Override
	public void deletePurchase(int purchaseId) {
		this.purchaseRepository.deleteById(purchaseId);

	}

}
