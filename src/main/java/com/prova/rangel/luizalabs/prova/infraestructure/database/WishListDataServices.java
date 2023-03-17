package com.prova.rangel.luizalabs.prova.infraestructure.database;

import java.util.List;
import java.util.Optional;

import com.prova.rangel.luizalabs.prova.domain.entity.WishList;
import com.prova.rangel.luizalabs.prova.infraestructure.database.model.WishListModel;

public interface WishListDataServices {
	
	WishListModel save(String idWishList, WishList wishList);
	
	Optional<WishListModel> findWishListByClientIdAndId(String clientId, String wishListId);
	
	Optional<WishListModel> findWishListById(String wishListId);
	
	List<WishListModel> findWishListByNameLike(String name);
	
}
