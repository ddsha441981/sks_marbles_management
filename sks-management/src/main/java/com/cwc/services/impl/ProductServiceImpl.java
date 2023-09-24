package com.cwc.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwc.exception.ResourceNotFoundException;
import com.cwc.model.Product;
import com.cwc.repository.ProductRespository;
import com.cwc.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRespository productRespository;
	
	@Override
	public Product addProduct(Product product) {
		System.out.println(product.toString());
		Product savedProduct = this.productRespository.save(product);
		return savedProduct;
	}

	@Override
	public Product updateProduct(Product product, int productId) {
		Product products = this.productRespository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Resource not found with this "+ productId + " Id"));
		products.setProductName(product.getProductName());
		products.setPricePerUnit(product.getPricePerUnit());
		products.setQuantity(product.getQuantity());
		products.setProductDescription(product.getProductDescription());
		products.setProductCondition(product.getProductCondition());
		products.setProductStatus(product.getProductStatus());
		products.setUpdatedAt(product.getUpdatedAt());
		Product updatedProduct = this.productRespository.save(products);
		return updatedProduct;
	}

	@Override
	public Product singleProduct(int productId) {
		return  this.productRespository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Resource not found with this "+ productId + " Id"));
	}

	@Override
	public List<Product> retriveAllProducts() {
		return this.productRespository.findAll();
	}

	@Override
	public void deleteProduct(int productId) {
		this.productRespository.deleteById(productId);

	}

	@Override
	public List<Product> getProductByCategory(int categoryId) {
		
		return  this.productRespository.findByCategoryCategoryId(categoryId);
	}

}
