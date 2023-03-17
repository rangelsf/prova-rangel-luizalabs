package com.prova.rangel.luizalabs.prova.domain.entity.factory.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.prova.rangel.luizalabs.prova.domain.entity.WishList;
import com.prova.rangel.luizalabs.prova.domain.entity.factory.WishListFactory;
import com.prova.rangel.luizalabs.prova.domain.entity.impl.WishListImpl;

@Component
public class WishListFactoryImpl implements WishListFactory {

	@Override
	public WishList createNewWishList(String clientId, List<String> productIdList, String name) {
		// TODO return the new impl object when class created
		
		return new WishListImpl(productIdList, clientId, name);
	}

}
