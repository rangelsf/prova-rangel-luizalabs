package com.prova.rangel.luizalabs.prova.domain.entity;

import java.util.List;

public interface WishList {
	
	boolean isFullWishlist();
	
	List<String> getProductIdList();
	
	String getName();
	
	String getClientId();
}
