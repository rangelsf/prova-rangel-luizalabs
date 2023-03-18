package com.prova.rangel.luizalabs.prova.domain.usecase;

import com.prova.rangel.luizalabs.prova.domain.response.FindIfProductIsOnWishListResponse;

public interface FindIfProductIsOnWishList {
	
	FindIfProductIsOnWishListResponse findIfProductIsOnWishList(String wishListId, String productId);
}
