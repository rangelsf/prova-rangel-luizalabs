package com.prova.rangel.luizalabs.prova.domain.response;


public class CreateWishListResponse {

	private String name;
	private String id;
	private String clientId;
	
	public CreateWishListResponse(String name, String id, String clientId) {
		super();
		this.name = name;
		this.id = id;
		this.clientId = clientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	
}
