package com.prova.rangel.luizalabs.prova.infraestructure.database.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prova.rangel.luizalabs.prova.infraestructure.database.ProductDataServices;
import com.prova.rangel.luizalabs.prova.infraestructure.database.model.ProductModel;
import com.prova.rangel.luizalabs.prova.infraestructure.database.repository.ProductRepository;

@Component
public class ProductDataServicesImpl implements ProductDataServices{

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public Optional<ProductModel> findByProductId(String productId) {
		return productRepository.findById(productId);
	}

	@Override
	public List<ProductModel> findByNameLike(String name) {
		return productRepository.findByNameLike(name);
	}

	@Override
	public List<ProductModel> findAllByProductIdIn(List<String> productIdList) {
		return StreamSupport.stream(productRepository.findAllById(productIdList).spliterator(), false)
		        .collect(Collectors.toList());
	}

}
