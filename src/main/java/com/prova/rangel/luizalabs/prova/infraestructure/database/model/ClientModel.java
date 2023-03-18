package com.prova.rangel.luizalabs.prova.infraestructure.database.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("client")
public class ClientModel {
	
	@Id
	private String clientId;
	
	private String name;
	
	
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ClientModel [clientId=" + clientId + ", name=" + name + "]";
	}
	
}
