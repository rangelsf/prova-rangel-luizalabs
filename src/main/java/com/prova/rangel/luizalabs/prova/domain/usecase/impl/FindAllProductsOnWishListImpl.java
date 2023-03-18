package com.prova.rangel.luizalabs.prova.domain.usecase.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prova.rangel.luizalabs.prova.domain.response.FindAllProductsOnWishListResponse;
import com.prova.rangel.luizalabs.prova.domain.response.FindWishListByIdResponse;
import com.prova.rangel.luizalabs.prova.domain.usecase.FindAllProductsOnWishList;
import com.prova.rangel.luizalabs.prova.domain.usecase.FindWishListById;
import com.prova.rangel.luizalabs.prova.exception.DataNotFoundException;
import com.prova.rangel.luizalabs.prova.exception.IncompleteRequestException;
import com.prova.rangel.luizalabs.prova.infraestructure.database.ProductDataServices;
import com.prova.rangel.luizalabs.prova.infraestructure.database.model.ProductModel;

@Service
public class FindAllProductsOnWishListImpl implements FindAllProductsOnWishList{

	@Autowired
	private ProductDataServices productDataServices;
	
	@Autowired
	private FindWishListById findWishListById;
	
	
	final static Logger log = LoggerFactory.getLogger(FindAllProductsOnWishListImpl.class);
	

	@Override
	public FindAllProductsOnWishListResponse findAllProductsOnWishList(String wishListId) {
		log.info("Finding all products on wish list");
		requestFieldVerification(wishListId);
		

		FindWishListByIdResponse findWishListByIdResponse = findWishListById.findWishListById(wishListId);
		
		if(findWishListByIdResponse.getProductIdList().isEmpty()) {
			log.info("No products on wish list");
			throw new DataNotFoundException("No products on this wish list");
		}
		
		return new FindAllProductsOnWishListResponse(productDataServices.findAllByProductIdIn(findWishListByIdResponse.getProductIdList()));
	}
	
	
	
	public void requestFieldVerification(String wishListId) {
		if(wishListId == null || wishListId.isBlank() || wishListId.isEmpty()) {
			log.info("wishList id empty");
			throw new IncompleteRequestException("WishList id is a mandatory field");	
		}
		
	}
	
	
	
}
