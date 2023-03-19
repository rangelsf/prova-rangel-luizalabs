package com.prova.rangel.luizalabs.prova.domain.usecase;

import com.prova.rangel.luizalabs.prova.domain.entity.factory.WishListFactory;
import com.prova.rangel.luizalabs.prova.domain.response.CreateWishListResponse;
import com.prova.rangel.luizalabs.prova.infraestructure.database.WishListDataServices;

public interface CreateWishList {
	
	CreateWishListResponse create(String clientId, String name);
	
}
