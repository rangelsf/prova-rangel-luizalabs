package com.prova.rangel.luizalabs.prova.domain.entity.impl;

import java.util.List;

import com.prova.rangel.luizalabs.prova.domain.entity.WishList;


public class WishListImpl implements WishList {
	
	private List<String> productIdList;
	private String clientId;
	private String wishListName;
	
	
	private final static int PRODUCT_LIST_MAX_SIZE = 20;
	
	
	public WishListImpl(List<String> productIdList, String clientId, String wishListName) {
		this.productIdList = productIdList;
		this.clientId = clientId;
		this.wishListName = wishListName;
	}

	@Override
	public boolean isFullWishlist() {
		if(this.productIdList.isEmpty()) {
			return false;
		}
		return productIdList.size() >= PRODUCT_LIST_MAX_SIZE;
	}

	@Override
	public List<String> getProductIdList() {
		return this.productIdList;
	}

	@Override
	public String getName() {
		return this.wishListName;
	}

	@Override
	public String getClientId() {
		return this.clientId;
	}

	
}
