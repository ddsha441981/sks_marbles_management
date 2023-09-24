package com.cwc.services;

import java.util.List;

import com.cwc.model.Purchase;

public interface PurchaseService {

	//add Purchase
	public Purchase addPurchase(Purchase purchase);
	//update Purchase
	public Purchase updatePurchase(Purchase purchase,int purchaseId);
	//get single Purchase
	public Purchase singlePurchase(int purchaseId);
	//get Purchase List
	public List<Purchase> getPurchaseList();
	//delete Purchase
	public void deletePurchase(int purchaseId);
}
