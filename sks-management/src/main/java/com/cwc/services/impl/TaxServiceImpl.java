package com.cwc.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwc.exception.ResourceNotFoundException;
import com.cwc.model.Tax;
import com.cwc.repository.TaxRespository;
import com.cwc.services.TaxService;

@Service
public class TaxServiceImpl implements TaxService {

	@Autowired
	private TaxRespository taxRespository;
	
	@Override
	public Tax addTax(Tax tax) {
		return this.taxRespository.save(tax);
	}

	@Override
	public Tax updateTax(Tax tax, int taxId) {
		Tax fetchedTex = this.taxRespository.findById(taxId).orElseThrow(()-> new ResourceNotFoundException("Resource not found with this tax id {} "));
		fetchedTex.setTaxType(tax.getTaxType());
		fetchedTex.setRate(tax.getRate());
		return this.taxRespository.save(fetchedTex);
	}

	@Override
	public Tax singleTax(int taxId) {
		return this.taxRespository.findById(taxId).orElseThrow(()-> new ResourceNotFoundException("Resource not found with this tax id {} "));
	}

	@Override
	public List<Tax> getTaxList() {
		return this.taxRespository.findAll();
	}

	@Override
	public void deleteTax(int taxId) {
		this.taxRespository.deleteById(taxId);
	}

}
