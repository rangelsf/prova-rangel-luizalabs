package com.prova.rangel.luizalabs.prova.domain.usecase.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prova.rangel.luizalabs.prova.domain.entity.WishList;
import com.prova.rangel.luizalabs.prova.domain.entity.factory.WishListFactory;
import com.prova.rangel.luizalabs.prova.domain.response.FindWishListByIdResponse;
import com.prova.rangel.luizalabs.prova.domain.usecase.DeleteProductByIdFromWishList;
import com.prova.rangel.luizalabs.prova.domain.usecase.FindWishListById;
import com.prova.rangel.luizalabs.prova.exception.DataNotFoundException;
import com.prova.rangel.luizalabs.prova.exception.IncompleteRequestException;
import com.prova.rangel.luizalabs.prova.infraestructure.database.WishListDataServices;

@Service
public class DeleteProductByIdFromWishListImpl implements DeleteProductByIdFromWishList{

	@Autowired
	private WishListDataServices wishListDataservices;
	
	@Autowired
	private FindWishListById findWishListById;
	
	@Autowired
	private WishListFactory wishListFactory;
	
	
	static final Logger log = LoggerFactory.getLogger(DeleteProductByIdFromWishListImpl.class);
	
	@Override
	public void deleteProductByIdFromWishList(String productId, String wishListId) {
		requestFieldVerification(wishListId, productId);
		
		FindWishListByIdResponse findWishListByIdResponse = findWishListById.findWishListById(wishListId);
		
		if(!findWishListByIdResponse.getProductIdList().contains(productId)) {
			log.info("product not found on the list");
			throw new DataNotFoundException("Product is not on the wish list");
		}
		
		findWishListByIdResponse.getProductIdList().remove(productId);
		
		WishList wishListModel = wishListFactory.createNewWishList(findWishListByIdResponse.getClientId(), findWishListByIdResponse.getProductIdList(), findWishListByIdResponse.getName());
				
		wishListDataservices.save(findWishListByIdResponse.getId(), wishListModel);
		log.info("product removed from wish list");
		
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



	public DeleteProductByIdFromWishListImpl(WishListDataServices wishListDataservices,
			FindWishListById findWishListById, WishListFactory wishListFactory) {
		super();
		this.wishListDataservices = wishListDataservices;
		this.findWishListById = findWishListById;
		this.wishListFactory = wishListFactory;
	}
	
	

}
