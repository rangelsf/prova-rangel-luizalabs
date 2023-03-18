package com.prova.rangel.luizalabs.prova.domain.usecase.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prova.rangel.luizalabs.prova.domain.entity.WishList;
import com.prova.rangel.luizalabs.prova.domain.entity.factory.WishListFactory;
import com.prova.rangel.luizalabs.prova.domain.request.AddProductOnWishListRequest;
import com.prova.rangel.luizalabs.prova.domain.response.AddProductOnWishListResponse;
import com.prova.rangel.luizalabs.prova.domain.response.FindIfProductIsOnWishListResponse;
import com.prova.rangel.luizalabs.prova.domain.usecase.AddProductOnWishList;
import com.prova.rangel.luizalabs.prova.domain.usecase.FindIfProductExistsById;
import com.prova.rangel.luizalabs.prova.domain.usecase.FindIfProductIsOnWishList;
import com.prova.rangel.luizalabs.prova.exception.BusinessRuleException;
import com.prova.rangel.luizalabs.prova.exception.DataNotFoundException;
import com.prova.rangel.luizalabs.prova.exception.IncompleteRequestException;
import com.prova.rangel.luizalabs.prova.infraestructure.controller.WishListController;
import com.prova.rangel.luizalabs.prova.infraestructure.database.WishListDataServices;
import com.prova.rangel.luizalabs.prova.infraestructure.database.model.WishListModel;
import com.prova.rangel.luizalabs.prova.infraestructure.database.model.ProductModel;
import com.prova.rangel.luizalabs.prova.infraestructure.database.repository.ProductRepository;

@Service
public class AddProductOnWishListImpl implements AddProductOnWishList{

	@Autowired
	private WishListDataServices wishListDataServices;
	
	@Autowired
	private WishListFactory wishListFactory;
	
	@Autowired
	FindIfProductIsOnWishList findIfProductIsOnWishList;
	
	@Autowired
	FindIfProductExistsById findIfProductExistsById;

	
	final static Logger log = LoggerFactory.getLogger(AddProductOnWishListImpl.class);
	
	@Override
	public AddProductOnWishListResponse add(AddProductOnWishListRequest addProductOnWishListRequest) {
		log.info("Searching WishList to insert product");
		requestFieldVerification(addProductOnWishListRequest);
		
		Optional<WishListModel> optionalWishListModel = wishListDataServices.findWishListByClientIdAndId(
				addProductOnWishListRequest.getClientId(), 
				addProductOnWishListRequest.getWishListId()
				);
		
		if(!optionalWishListModel.isPresent()) {
			log.info("Wish List not found");
			throw new DataNotFoundException("Wish list not found.");
		}
		WishListModel wishListModel = optionalWishListModel.get();
		
		
		if(!findIfProductExistsById.findIfProductExistsById(addProductOnWishListRequest.getProductId())) {
			throw new DataNotFoundException("Product not found.");
		}
		
		
		
		FindIfProductIsOnWishListResponse findIfProductIsOnWishListResponse =
				findIfProductIsOnWishList.findIfProductIsOnWishList(
				addProductOnWishListRequest.getWishListId(), 
				addProductOnWishListRequest.getProductId());
		
		if(findIfProductIsOnWishListResponse.isExists()) {
			throw new BusinessRuleException("Product already on wish list");
		}
		
		
		WishList wishList = wishListFactory.createNewWishList(
				wishListModel.getClientId(), 
				wishListModel.getProductIdList(), 
				wishListModel.getName()
				);
		
		if(wishList.isFullWishlist()) {
			throw new BusinessRuleException("Wish list is full");
		}
		
		wishList.getProductIdList().add(addProductOnWishListRequest.getProductId());
		
		WishListModel wishListSaved = wishListDataServices.save(wishListModel.getWishListId(), wishList);

		log.info("Product added to WishList");
		
		return new AddProductOnWishListResponse(
				wishListSaved.getWishListId(),
				wishListSaved.getClientId(), 
				wishListSaved.getProductIdList()
				);
	}
	
	
	public void requestFieldVerification(AddProductOnWishListRequest addProductOnWishListRequest) {
		String clientId = addProductOnWishListRequest.getClientId();
		if(clientId == null || clientId.isBlank() || clientId.isEmpty()) {
			log.info("client id empty");
			throw new IncompleteRequestException("Client id is a mandatory field");	
		}
		
		String productId = addProductOnWishListRequest.getProductId();				
		if(productId == null || productId.isEmpty() || productId.isBlank()) {
			log.info("productId empty");
			throw new IncompleteRequestException("Product id is a mandatory field");	
		}
		
		String wishListId = addProductOnWishListRequest.getWishListId();
		if(wishListId == null || wishListId.isEmpty() || wishListId.isBlank()) {
			log.info("wishListId empty");
			throw new IncompleteRequestException("WishList Id is a mandatory field");	
		}
		
	}

	
	
}
