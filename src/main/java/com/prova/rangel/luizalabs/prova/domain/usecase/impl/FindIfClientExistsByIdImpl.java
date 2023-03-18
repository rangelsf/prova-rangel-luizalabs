package com.prova.rangel.luizalabs.prova.domain.usecase.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prova.rangel.luizalabs.prova.domain.usecase.FindIfClientExistsById;
import com.prova.rangel.luizalabs.prova.infraestructure.database.ClientDataServices;

@Service
public class FindIfClientExistsByIdImpl implements FindIfClientExistsById{

	@Autowired
	private ClientDataServices clientDataServices;
	
	@Override
	public boolean findIfClientExistsById(String clientId) {
		
		
		return clientDataServices.findByClientId(clientId).isPresent();
	}

}
