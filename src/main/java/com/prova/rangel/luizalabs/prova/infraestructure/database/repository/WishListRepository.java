package com.prova.rangel.luizalabs.prova.infraestructure.database.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.prova.rangel.luizalabs.prova.infraestructure.database.model.WishListModel;

@Repository
public interface WishListRepository extends MongoRepository<WishListModel, String>{

	List<WishListModel> findByNameLike(String name);
	Optional<WishListModel> findByClientIdAndId(String clientId, String wishListId);
}
