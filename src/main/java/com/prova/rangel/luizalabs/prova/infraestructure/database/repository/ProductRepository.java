package com.prova.rangel.luizalabs.prova.infraestructure.database.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.prova.rangel.luizalabs.prova.infraestructure.database.model.ProductModel;

@Repository
public interface ProductRepository extends MongoRepository<ProductModel, String>{
	
	List<ProductModel> findByNameLike(String name);	

}
