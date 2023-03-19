package com.prova.rangel.luizalabs.prova.domain.usecase.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prova.rangel.luizalabs.prova.domain.usecase.FindIfProductExistsById;
import com.prova.rangel.luizalabs.prova.exception.IncompleteRequestException;
import com.prova.rangel.luizalabs.prova.infraestructure.database.ProductDataServices;

@Service
public class FindIfProductExistsByIdImpl implements FindIfProductExistsById{

	@Autowired
	private ProductDataServices productDataServices;
	
	final static Logger log = LoggerFactory.getLogger(FindIfProductExistsByIdImpl.class);
	
	@Override
	public boolean findIfProductExistsById(String productId) {
		requestFieldVerification(productId);
		return productDataServices.findByProductId(productId).isPresent();
	}
	
	public void requestFieldVerification(String productId) {
		if(productId == null || productId.isEmpty() || productId.isBlank()) {
			log.info("productId empty");
			throw new IncompleteRequestException("Product id is a mandatory field");	
		}
	}

	public FindIfProductExistsByIdImpl(ProductDataServices productDataServices) {
		super();
		this.productDataServices = productDataServices;
	}
	
	
	
}
