package com.prova.rangel.luizalabs.prova.domain.request;

import javax.validation.constraints.NotBlank;

public class AddProductOnWishListRequest {

	@NotBlank
	private String wishListId;

	@NotBlank
	private String productId;

	@NotBlank
	private String clientId;

	public String getWishListId() {
		return wishListId;
	}

	public void setWishListId(String wishListId) {
		this.wishListId = wishListId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	@Override
	public String toString() {
		return "AddProductOnWishListRequest [wishListId=" + wishListId + ", productId=" + productId + ", clientId="
				+ clientId + "]";
	}

}
