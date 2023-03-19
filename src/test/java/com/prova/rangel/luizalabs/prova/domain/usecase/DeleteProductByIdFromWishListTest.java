package com.prova.rangel.luizalabs.prova.domain.usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.prova.rangel.luizalabs.prova.domain.entity.WishList;
import com.prova.rangel.luizalabs.prova.domain.entity.factory.WishListFactory;
import com.prova.rangel.luizalabs.prova.domain.entity.factory.impl.WishListFactoryImpl;
import com.prova.rangel.luizalabs.prova.domain.response.FindWishListByIdResponse;
import com.prova.rangel.luizalabs.prova.domain.usecase.impl.DeleteProductByIdFromWishListImpl;
import com.prova.rangel.luizalabs.prova.exception.DataNotFoundException;
import com.prova.rangel.luizalabs.prova.exception.IncompleteRequestException;
import com.prova.rangel.luizalabs.prova.infraestructure.database.WishListDataServices;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class DeleteProductByIdFromWishListTest {

	DeleteProductByIdFromWishList deleteProductByIdFromWishList;

	@Mock
	private WishListDataServices wishListDataServices;

	@Mock
	private FindWishListById findWishListById;

	private WishListFactory wishListFactory = new WishListFactoryImpl();

	@Captor
	private ArgumentCaptor<WishList> wishListArgumentCaptor;

	@BeforeEach
	void init() {
		deleteProductByIdFromWishList = new DeleteProductByIdFromWishListImpl(wishListDataServices, findWishListById, wishListFactory);		
	}


	@DisplayName("If wish list and the product is not null and exists, should delete the product from the wish list")
	@Test
	void shouldRemoveProductsFromWishList(){
		List<String> products = new ArrayList<>();
		products.add("1");
		products.add("2");
		FindWishListByIdResponse findWishListByIdResponse = new FindWishListByIdResponse();
		findWishListByIdResponse.setClientId("1");
		findWishListByIdResponse.setId("1");
		findWishListByIdResponse.setName("name");
		findWishListByIdResponse.setProductIdList(products);


		Mockito.when(findWishListById.findWishListById("1")).thenReturn(findWishListByIdResponse);

		deleteProductByIdFromWishList.deleteProductByIdFromWishList("1", "1");

		Mockito.verify(wishListDataServices).save(ArgumentMatchers.any(), wishListArgumentCaptor.capture());
		WishList savedWishList = wishListArgumentCaptor.getValue();
		Assertions.assertNotNull(savedWishList);
		Assertions.assertNotNull(savedWishList.getProductIdList());
		Assertions.assertEquals(1, savedWishList.getProductIdList().size());
	}

	@DisplayName("If wish list and product are not null, and the wish list does not have the product, should throw exception")
	@Test
	void shouldThrowWishListDontHaveTheProduct(){
		FindWishListByIdResponse findWishListByIdResponse = new FindWishListByIdResponse();
		findWishListByIdResponse.setClientId("1");
		findWishListByIdResponse.setId("1");
		findWishListByIdResponse.setName("name");
		findWishListByIdResponse.setProductIdList(List.of());

		Mockito.when(findWishListById.findWishListById("1")).thenReturn(findWishListByIdResponse);

		org.assertj.core.api.Assertions.assertThatThrownBy(
				() -> deleteProductByIdFromWishList.deleteProductByIdFromWishList("1", "1"))
		.isInstanceOf(DataNotFoundException.class)
		.hasMessage("Product is not on the wish list");
	}

	@DisplayName("If the product is null, should throw exception")
	@Test
	void shouldThrowWhenProductIsNull(){
		org.assertj.core.api.Assertions.assertThatThrownBy(
				() -> deleteProductByIdFromWishList.deleteProductByIdFromWishList(null, "1"))
		.isInstanceOf(IncompleteRequestException.class)
		.hasMessage("Product id is a mandatory field");
	}

	@DisplayName("If the wish list is null, should throw exception")
	@Test
	void shouldThrowWhenWishListIsNull(){
		org.assertj.core.api.Assertions.assertThatThrownBy(
				() -> deleteProductByIdFromWishList.deleteProductByIdFromWishList("1", null))
		.isInstanceOf(IncompleteRequestException.class)
		.hasMessage("WishList id is a mandatory field");
	}

}
