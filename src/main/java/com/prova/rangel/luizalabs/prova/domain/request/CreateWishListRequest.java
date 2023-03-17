package com.prova.rangel.luizalabs.prova.domain.request;

import javax.validation.constraints.NotBlank;

import lombok.ToString;

public class CreateWishListRequest {

	@NotBlank
	private String clientId;
	
	@NotBlank
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
		return "CreateWishListRequest [clientId=" + clientId + ", name=" + name + "]";
	}
	
	

}
