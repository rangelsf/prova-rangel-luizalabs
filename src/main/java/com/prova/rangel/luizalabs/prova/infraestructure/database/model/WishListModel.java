package com.prova.rangel.luizalabs.prova.infraestructure.database.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document("wishlist")
public class WishListModel {

	@Id
	private String id;
	
	private String clientId;
	
	private String name;
	
	private List<String> productIdList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getProductIdList() {
		return productIdList;
	}

	public void setProductIdList(List<String> productIdList) {
		this.productIdList = productIdList;
	}
	
	
	
}
