package com.cwc.services;

import java.util.List;

import com.cwc.model.Supplier;

public interface SupplierService {

	//add Supplier
	public Supplier addSupplier(Supplier supplier);
	//update Supplier
	public Supplier updateSupplier(Supplier supplier,int supplierId);
	//get Single Supplier
	public Supplier singleSupplier(int supplierId);
	//get All Supplier
	public List<Supplier> getSupplierList();
	//delete Supplier
	public void deleteSupplier(int supplierId);
}
