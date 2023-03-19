package com.prova.rangel.luizalabs.prova.infraestructure.database.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prova.rangel.luizalabs.prova.exception.IncompleteRequestException;
import com.prova.rangel.luizalabs.prova.infraestructure.database.ClientDataServices;
import com.prova.rangel.luizalabs.prova.infraestructure.database.model.ClientModel;
import com.prova.rangel.luizalabs.prova.infraestructure.database.repository.ClientRepository;

@Component
public class ClientDataServicesImpl implements ClientDataServices{

	@Autowired
	private ClientRepository clientRepository;
	
	static final Logger log = LoggerFactory.getLogger(ClientDataServicesImpl.class);
	
	@Override
	public Optional<ClientModel> findByClientId(String clientId) {
		requestFieldVerification(clientId);
		return clientRepository.findById(clientId);
	}
	
	
	public void requestFieldVerification(String clientId) {
		if(clientId == null || clientId.isBlank() || clientId.isEmpty()) {
			log.info("client id empty");
			throw new IncompleteRequestException("ClientId is a mandatory field");	
		}
		
	}
	

}
