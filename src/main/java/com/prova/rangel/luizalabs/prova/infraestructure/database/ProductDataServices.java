package com.prova.rangel.luizalabs.prova.infraestructure.database;

import java.util.List;
import java.util.Optional;

import com.prova.rangel.luizalabs.prova.infraestructure.database.model.ProductModel;

public interface ProductDataServices {

	Optional<ProductModel> findByProductId(String productId);
	List<ProductModel> findByNameLike(String name);
	List<ProductModel> findAllByProductIdIn(List<String> productIdList);
}
