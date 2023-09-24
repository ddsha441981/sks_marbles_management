package com.cwc.services;

import java.util.List;

import com.cwc.model.Product;

public interface ProductService {
	
	//add product
	public Product addProduct(Product product);
	//update product
	public Product updateProduct(Product product,int productId);
	//get single product
	public Product singleProduct(int productId);
	//get all product
	public List<Product> retriveAllProducts();
	//delete product
	public void deleteProduct(int productId);
	public List<Product> getProductByCategory(int categoryId);
	

}
