package com.prova.rangel.luizalabs.prova.infraestructure.database.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.prova.rangel.luizalabs.prova.infraestructure.database.model.ClientModel;

@Repository
public interface ClientRepository extends MongoRepository<ClientModel, String>{

}
