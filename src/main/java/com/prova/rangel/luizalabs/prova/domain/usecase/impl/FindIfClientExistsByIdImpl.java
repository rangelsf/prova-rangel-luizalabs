package com.prova.rangel.luizalabs.prova.domain.usecase.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prova.rangel.luizalabs.prova.domain.usecase.FindIfClientExistsById;
import com.prova.rangel.luizalabs.prova.exception.IncompleteRequestException;
import com.prova.rangel.luizalabs.prova.infraestructure.database.ClientDataServices;

@Service
public class FindIfClientExistsByIdImpl implements FindIfClientExistsById{

	@Autowired
	private ClientDataServices clientDataServices;
	
	final static Logger log = LoggerFactory.getLogger(FindIfClientExistsByIdImpl.class);
	
	
	@Override
	public boolean findIfClientExistsById(String clientId) {
		requestFieldVerification(clientId);
		return clientDataServices.findByClientId(clientId).isPresent();
	}
	
	
	public void requestFieldVerification(String clientId) {
		if(clientId == null || clientId.isBlank() || clientId.isEmpty()) {
			log.info("client id empty");
			throw new IncompleteRequestException("clientId id is a mandatory field");	
		}
		
	}


	public FindIfClientExistsByIdImpl(ClientDataServices clientDataServices) {
		super();
		this.clientDataServices = clientDataServices;
	}
	
}
