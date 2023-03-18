package com.prova.rangel.luizalabs.prova.domain.response;

import java.util.List;

public class AddProductOnWishListResponse{

	private String wishListId;
	private String clientId;
	private List<String> productIdList;
	
	public AddProductOnWishListResponse(String wishListId, String clientId, List<String> productIdList) {
		this.wishListId = wishListId;
		this.clientId = clientId;
		this.productIdList = productIdList;
	}
	
	public String getWishListId() {
		return wishListId;
	}
	
	public void setWishListId(String wishListId) {
		this.wishListId = wishListId;
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
