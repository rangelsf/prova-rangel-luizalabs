package com.prova.rangel.luizalabs.prova.domain.usecase.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prova.rangel.luizalabs.prova.domain.response.FindWishListByIdResponse;
import com.prova.rangel.luizalabs.prova.domain.usecase.FindWishListById;
import com.prova.rangel.luizalabs.prova.exception.DataNotFoundException;
import com.prova.rangel.luizalabs.prova.exception.IncompleteRequestException;
import com.prova.rangel.luizalabs.prova.infraestructure.database.WishListDataServices;
import com.prova.rangel.luizalabs.prova.infraestructure.database.model.WishListModel;

@Service
public class FindWishListByIdImpl implements FindWishListById{

	@Autowired
	private WishListDataServices wishListDataServices;
	
	
	final static Logger log = LoggerFactory.getLogger(FindWishListByIdImpl.class);
	
	@Override
	public FindWishListByIdResponse findWishListById(String wishListId) {
		requestFieldVerification(wishListId);
		
		Optional<WishListModel> wishListModel = wishListDataServices.findWishListById(wishListId);
		
		if(!wishListModel.isPresent()) {
			throw new DataNotFoundException("Wish list not found.");
		}
		
		return new FindWishListByIdResponse(wishListModel.get().getName(), 
				wishListModel.get().getWishListId(), 
				wishListModel.get().getClientId(), 
				wishListModel.get().getProductIdList()
				);
	}
	
	
	public void requestFieldVerification(String wishListId) {
		if(wishListId == null || wishListId.isBlank() || wishListId.isEmpty()) {
			log.info("wishList id empty");
			throw new IncompleteRequestException("WishList id is a mandatory field");	
		}
		
	}

}
