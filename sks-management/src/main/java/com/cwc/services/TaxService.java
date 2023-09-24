package com.cwc.services;

import java.util.List;

import com.cwc.model.Tax;

public interface TaxService {

		//add Tax
		public Tax addTax(Tax tax);
		//update Tax
		public Tax updateTax(Tax tax,int taxId);
		//get Single Tax
		public Tax singleTax(int taxId);
		//get All Tax
		public List<Tax> getTaxList();
		//delete Tax
		public void deleteTax(int taxId);
}
