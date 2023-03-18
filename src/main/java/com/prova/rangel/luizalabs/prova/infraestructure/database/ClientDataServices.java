package com.prova.rangel.luizalabs.prova.infraestructure.database;

import java.util.Optional;

import com.prova.rangel.luizalabs.prova.infraestructure.database.model.ClientModel;

public interface ClientDataServices {

	Optional<ClientModel> findByClientId(String clientId);
}
