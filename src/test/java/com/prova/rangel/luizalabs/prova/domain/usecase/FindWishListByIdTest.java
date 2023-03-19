package com.prova.rangel.luizalabs.prova.domain.usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.prova.rangel.luizalabs.prova.domain.response.FindWishListByIdResponse;
import com.prova.rangel.luizalabs.prova.domain.usecase.impl.FindWishListByIdImpl;
import com.prova.rangel.luizalabs.prova.exception.DataNotFoundException;
import com.prova.rangel.luizalabs.prova.exception.IncompleteRequestException;
import com.prova.rangel.luizalabs.prova.infraestructure.database.WishListDataServices;
import com.prova.rangel.luizalabs.prova.infraestructure.database.model.WishListModel;

@ExtendWith(MockitoExtension.class)
class FindWishListByIdTest {

	FindWishListById findWishListById;

	@Mock
	private WishListDataServices wishListDataServices;


	@BeforeEach
	void init(){
		findWishListById = new FindWishListByIdImpl(wishListDataServices);
	}

	@DisplayName("Should wish list is not blank, null and exists, should return te wish list")
	@Test
	void shouldFind(){
		WishListModel wishListModel = new WishListModel();
		wishListModel.setClientId("1");
		wishListModel.setName("name 1");
		wishListModel.setProductIdList(List.of("1"));
		wishListModel.setWishListId("1");


		Mockito.when(wishListDataServices.findWishListById("1"))
		.thenReturn(Optional.of(wishListModel));

		FindWishListByIdResponse wishlistResponse = findWishListById.findWishListById("1");

		Assertions.assertNotNull(wishlistResponse);
		Assertions.assertNotNull(wishlistResponse.getClientId());
		Assertions.assertNotNull(wishlistResponse.getId());
		Assertions.assertNotNull(wishlistResponse.getName());
		Assertions.assertNotNull(wishlistResponse.getProductIdList());
		Assertions.assertEquals("1", wishlistResponse.getId());
	}

	@DisplayName("If the wish list id is not null or empty, and wish list not exist, should throw exception")
	@Test
	void shouldThrowWishListDoesNotExist(){
		Mockito.when(wishListDataServices.findWishListById("1")).thenReturn(Optional.empty());

		org.assertj.core.api.Assertions.assertThatThrownBy(
				() -> findWishListById.findWishListById("1"))
		.isInstanceOf(DataNotFoundException.class)
		.hasMessage("Wish list not found");
	}

	@DisplayName("If wish list id is null, should throw exception")
	@Test
	void shouldThrowIfWishListIdIsNull(){
		org.assertj.core.api.Assertions.assertThatThrownBy(
				() -> findWishListById.findWishListById(null))
		.isInstanceOf(IncompleteRequestException.class)
		.hasMessage("WishList id is a mandatory field");
	}
}
