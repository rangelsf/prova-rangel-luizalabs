package com.prova.rangel.luizalabs.prova.infraestructure.database.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("product")
public class ProductModel {

	@Id
	private String productId;
	
	private String name;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ProductModel [productId=" + productId + ", name=" + name + "]";
	}
	
}
