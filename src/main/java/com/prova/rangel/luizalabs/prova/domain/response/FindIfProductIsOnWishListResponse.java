package com.prova.rangel.luizalabs.prova.domain.response;

public class FindIfProductIsOnWishListResponse {

	private boolean exists;

	public FindIfProductIsOnWishListResponse(boolean exists) {
		this.exists = exists;
	}

	public boolean isExists() {
		return exists;
	}

	public void setExists(boolean exists) {
		this.exists = exists;
	}
	
	
}
