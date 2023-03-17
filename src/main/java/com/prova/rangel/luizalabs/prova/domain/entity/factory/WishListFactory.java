package com.prova.rangel.luizalabs.prova.domain.entity.factory;

import java.util.List;

import com.prova.rangel.luizalabs.prova.domain.entity.WishList;

public interface WishListFactory {
	
	WishList createNewWishList(String clientId, List<String> productIdList, String name);
}
