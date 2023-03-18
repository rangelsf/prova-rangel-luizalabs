package com.prova.rangel.luizalabs.prova.domain.usecase;

import com.prova.rangel.luizalabs.prova.domain.response.FindWishListByIdResponse;

public interface FindWishListById {

	FindWishListByIdResponse findWishListById(String wishListId);
}
