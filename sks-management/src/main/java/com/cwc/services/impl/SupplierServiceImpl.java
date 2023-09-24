package com.cwc.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwc.exception.ResourceNotFoundException;
import com.cwc.model.Supplier;
import com.cwc.repository.SupplierRespository;
import com.cwc.services.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	private SupplierRespository supplierRespository;
	
	@Override
	public Supplier addSupplier(Supplier supplier) {
		return this.supplierRespository.save(supplier);
	}

	@Override
	public Supplier updateSupplier(Supplier supplier, int supplierId) {
		Supplier fetchSupplier = this.supplierRespository.findById(supplierId).orElseThrow(()-> new ResourceNotFoundException("Resource not found with this supplier id {}"));
		fetchSupplier.setSupplierName(supplier.getSupplierName());
		fetchSupplier.setContactInfo(supplier.getContactInfo());
		fetchSupplier.setLocationInfo(supplier.getLocationInfo());
		return this.supplierRespository.save(fetchSupplier);
	}

	@Override
	public Supplier singleSupplier(int supplierId) {
		return this.supplierRespository.findById(supplierId).orElseThrow(()-> new ResourceNotFoundException("Resource not found with this supplier id {}"));
	}

	@Override
	public List<Supplier> getSupplierList() {
		return this.supplierRespository.findAll();
	}

	@Override
	public void deleteSupplier(int supplierId) {
		this.supplierRespository.deleteById(supplierId);

	}

}
