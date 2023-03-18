package com.prova.rangel.luizalabs.prova.domain.usecase.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prova.rangel.luizalabs.prova.domain.response.FindIfProductIsOnWishListResponse;
import com.prova.rangel.luizalabs.prova.domain.response.FindWishListByIdResponse;
import com.prova.rangel.luizalabs.prova.domain.usecase.FindIfProductIsOnWishList;
import com.prova.rangel.luizalabs.prova.domain.usecase.FindWishListById;
import com.prova.rangel.luizalabs.prova.exception.IncompleteRequestException;

@Service
public class FindIfProductIsOnWishListImpl implements FindIfProductIsOnWishList{

	@Autowired
	private FindWishListById findWishListById;
	
	final static Logger log = LoggerFactory.getLogger(FindIfProductIsOnWishListImpl.class);
	
	@Override
	public FindIfProductIsOnWishListResponse findIfProductIsOnWishList(String wishListId, String productId) {
		log.info("Finding if product is on wish list");
		requestFieldVerification(wishListId, productId);
		
		FindWishListByIdResponse findWishListByIdResponse = findWishListById.findWishListById(wishListId);
		
		if(findWishListByIdResponse.getProductIdList().isEmpty()) {
			log.info("Wish list empty");
			return new FindIfProductIsOnWishListResponse(false);
		}

		if(findWishListByIdResponse.getProductIdList().contains(productId)) {
			log.info("Product found");
			return new FindIfProductIsOnWishListResponse(true);
		}else {
			log.info("Product not found");
			return new FindIfProductIsOnWishListResponse(false);
		}
		
	}
	
	
	
	public void requestFieldVerification(String wishListId, String productId) {
		if(wishListId == null || wishListId.isBlank() || wishListId.isEmpty()) {
			log.info("wishList id empty");
			throw new IncompleteRequestException("WishList id is a mandatory field");	
		}
		
		if(productId == null || productId.isEmpty() || productId.isBlank()) {
			log.info("productId empty");
			throw new IncompleteRequestException("Product id is a mandatory field");	
		}
		
	}
	

}
