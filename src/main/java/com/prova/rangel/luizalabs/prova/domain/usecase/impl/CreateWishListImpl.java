package com.prova.rangel.luizalabs.prova.domain.usecase.impl;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prova.rangel.luizalabs.prova.domain.entity.WishList;
import com.prova.rangel.luizalabs.prova.domain.entity.factory.WishListFactory;
import com.prova.rangel.luizalabs.prova.domain.response.CreateWishListResponse;
import com.prova.rangel.luizalabs.prova.domain.usecase.CreateWishList;
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
		// TODO ADICIONAR VERIFICACAO DE NULO
		log.info("parar aqui");
		WishList wishList = wishListFactory.createNewWishList(clientId, Arrays.asList(), name);
		
		WishListModel wishListModel = wishListDataServices.save(null, wishList);
		
		return new CreateWishListResponse(wishListModel.getName(), wishListModel.getId(), wishListModel.getClientId());
	}

}
