package com.prova.rangel.luizalabs.prova.domain.usecase;

import com.prova.rangel.luizalabs.prova.domain.response.CreateWishListResponse;

public interface CreateWishList {
	
	CreateWishListResponse create(String clientId, String name);
}
