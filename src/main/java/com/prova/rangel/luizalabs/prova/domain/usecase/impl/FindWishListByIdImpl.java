package com.prova.rangel.luizalabs.prova.domain.usecase.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prova.rangel.luizalabs.prova.domain.response.FindWishListByIdResponse;
import com.prova.rangel.luizalabs.prova.domain.usecase.FindWishListById;
import com.prova.rangel.luizalabs.prova.infraestructure.database.WishListDataServices;
import com.prova.rangel.luizalabs.prova.infraestructure.database.model.WishListModel;

@Service
public class FindWishListByIdImpl implements FindWishListById{

	@Autowired
	private WishListDataServices wishListDataServices;
	
	@Override
	public FindWishListByIdResponse findWishListByIdResponse(String wishListId) {
		// TODO checagem de nulos
		
		Optional<WishListModel> wishListModel = wishListDataServices.findWishListById(wishListId);
		
		return new FindWishListByIdResponse(wishListModel.get().getName(), wishListModel.get().getId(), wishListModel.get().getClientId(), wishListModel.get().getProductIdList());
		
	
	}

}
