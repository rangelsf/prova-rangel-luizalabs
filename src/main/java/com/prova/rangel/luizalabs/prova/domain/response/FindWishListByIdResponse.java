package com.prova.rangel.luizalabs.prova.domain.response;

import java.util.List;

public class FindWishListByIdResponse {

	private String name;
	private String id;
	private String clientId;
	private List<String> productIdList;
	
	public FindWishListByIdResponse(String name, String id, String clientId, List<String> productIdList) {
		this.name = name;
		this.id = id;
		this.clientId = clientId;
		this.productIdList = productIdList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public List<String> getProductIdList() {
		return productIdList;
	}

	public void setProductIdList(List<String> productIdList) {
		this.productIdList = productIdList;
	}

	
	
	
}
