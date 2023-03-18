package com.prova.rangel.luizalabs.prova.domain.usecase.impl;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prova.rangel.luizalabs.prova.domain.entity.WishList;
import com.prova.rangel.luizalabs.prova.domain.entity.factory.WishListFactory;
import com.prova.rangel.luizalabs.prova.domain.request.AddProductOnWishListRequest;
import com.prova.rangel.luizalabs.prova.domain.response.CreateWishListResponse;
import com.prova.rangel.luizalabs.prova.domain.usecase.CreateWishList;
import com.prova.rangel.luizalabs.prova.exception.IncompleteRequestException;
import com.prova.rangel.luizalabs.prova.infraestructure.controller.WishListController;
import com.prova.rangel.luizalabs.prova.infraestructure.database.WishListDataServices;
import com.prova.rangel.luizalabs.prova.infraestructure.database.model.WishListModel;

@Service
public class CreateWishListImpl implements CreateWishList{

	@Autowired
	private WishListFactory wishListFactory;
	
	@Autowired
	private WishListDataServices wishListDataServices;
	
	final static Logger log = LoggerFactory.getLogger(CreateWishListImpl.class);
	
	
	@Override
	public CreateWishListResponse create(String clientId, String name) {
		log.info("Creating new wish list");
		requestFieldVerification(clientId, name);
		
		// TODO ADICIONAR VERIFICACAO DE se cliente existe
		

		WishList wishList = wishListFactory.createNewWishList(clientId, Arrays.asList(), name);
		
		WishListModel wishListModel = wishListDataServices.save(null, wishList);
		log.info("Wish list created");
		
		return new CreateWishListResponse(wishListModel.getName(), wishListModel.getWishListId(), wishListModel.getClientId());
	}
	
	
	
	public void requestFieldVerification(String clientId, String name) {
		if(clientId == null || clientId.isBlank() || clientId.isEmpty()) {
			log.info("client id empty");
			throw new IncompleteRequestException("Client id is a mandatory field");	
		}
		
		if(name == null || name.isEmpty() || name.isBlank()) {
			log.info("name empty");
			throw new IncompleteRequestException("name is a mandatory field");	
		}
		
	}

}
