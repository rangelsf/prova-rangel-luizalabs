package com.prova.rangel.luizalabs.prova.infraestructure.database.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.prova.rangel.luizalabs.prova.domain.entity.WishList;
import com.prova.rangel.luizalabs.prova.infraestructure.database.WishListDataServices;
import com.prova.rangel.luizalabs.prova.infraestructure.database.model.WishListModel;
import com.prova.rangel.luizalabs.prova.infraestructure.database.repository.WishListRepository;

@Component
public class WishListDataServicesImpl implements WishListDataServices{

	@Autowired
	private WishListRepository wishListRepository;
	
	@Override
	public WishListModel save(String idWishList, WishList wishList) {
		WishListModel wishListModel = new WishListModel();
		wishListModel.setWishListId(idWishList);
		wishListModel.setClientId(wishList.getClientId());
		wishListModel.setName(wishList.getName());
		wishListModel.setProductIdList(wishList.getProductIdList());
		return wishListRepository.save(wishListModel);
	}

	@Override
	public Optional<WishListModel> findWishListByClientIdAndId(String clientId, String wishListId) {
		return wishListRepository.findByClientIdAndWishListId(clientId, wishListId);
	}

	@Override
	public Optional<WishListModel> findWishListById(String wishListId) {
		return wishListRepository.findById(wishListId);
	}

	@Override
	public List<WishListModel> findWishListByNameLike(String name) {
		return wishListRepository.findByNameLike(name);
	}

	
}
