package com.prova.rangel.luizalabs.prova.domain.usecase;

import com.prova.rangel.luizalabs.prova.domain.response.FindAllProductsOnWishListResponse;

public interface FindAllProductsOnWishList {
	
	FindAllProductsOnWishListResponse findAllProductsOnWishList(String wishListId);
}
