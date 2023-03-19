package com.prova.rangel.luizalabs.prova.domain.response;

import java.util.ArrayList;
import java.util.List;

import com.prova.rangel.luizalabs.prova.infraestructure.database.model.ProductModel;


public class FindAllProductsOnWishListResponse {

	private List<ProductModel> productList = new ArrayList<>();

	
	public FindAllProductsOnWishListResponse(List<ProductModel> productList) {
		this.productList = productList;
	}
	
	public FindAllProductsOnWishListResponse() {
	
	}

	public List<ProductModel> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductModel> productList) {
		this.productList = productList;
	}

}
