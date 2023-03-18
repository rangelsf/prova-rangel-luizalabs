package com.prova.rangel.luizalabs.prova.domain.usecase;

import com.prova.rangel.luizalabs.prova.domain.request.AddProductOnWishListRequest;
import com.prova.rangel.luizalabs.prova.domain.response.AddProductOnWishListResponse;

public interface AddProductOnWishList {

	AddProductOnWishListResponse add(AddProductOnWishListRequest addProductOnWishListRequest);
}
