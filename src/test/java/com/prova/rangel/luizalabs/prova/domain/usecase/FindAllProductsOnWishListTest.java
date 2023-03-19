package com.prova.rangel.luizalabs.prova.domain.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.prova.rangel.luizalabs.prova.domain.usecase.impl.FindAllProductsOnWishListImpl;
import com.prova.rangel.luizalabs.prova.exception.DataNotFoundException;
import com.prova.rangel.luizalabs.prova.exception.IncompleteRequestException;
import com.prova.rangel.luizalabs.prova.infraestructure.database.ProductDataServices;
import com.prova.rangel.luizalabs.prova.infraestructure.database.model.ProductModel;
import com.prova.rangel.luizalabs.prova.domain.response.FindWishListByIdResponse;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class FindAllProductsOnWishListTest {

	FindAllProductsOnWishList findAllProductsOnWishList;

	@Mock
	private ProductDataServices productDataServices;

	@Mock
	FindWishListById findWishListById;


	@BeforeEach
	void init() {
		findAllProductsOnWishList = new FindAllProductsOnWishListImpl(productDataServices, findWishListById);

	}


	@DisplayName("If there is no products on the wish list, should throw exception")
	@Test
    void shouldThrowExceptionIfNoProducts(){
		
		FindWishListByIdResponse findWishListByIdResponse = new FindWishListByIdResponse();
		findWishListByIdResponse.setClientId("1");
		findWishListByIdResponse.setId("1");
		findWishListByIdResponse.setName("name");
		findWishListByIdResponse.setProductIdList(List.of());

        Mockito.when(findWishListById.findWishListById("1")).thenReturn(findWishListByIdResponse);

        org.assertj.core.api.Assertions.assertThatThrownBy(
                () -> findAllProductsOnWishList.findAllProductsOnWishList("1"))
                .isInstanceOf(DataNotFoundException.class)
                .hasMessage("No products on this wish list");

    }
	
	@DisplayName("If id is null, should throw exception")
	@Test
    void shouldThrowExceptionWhenIdNull(){
        org.assertj.core.api.Assertions.assertThatThrownBy(
                () -> findAllProductsOnWishList.findAllProductsOnWishList(null))
                .isInstanceOf(IncompleteRequestException.class)
                .hasMessage("WishList id is a mandatory field");
    }

	@DisplayName("If id is blank, should throw exception")
	@Test
    void shouldThrowExceptionWhenIdBlank(){
        org.assertj.core.api.Assertions.assertThatThrownBy(
                () -> findAllProductsOnWishList.findAllProductsOnWishList(""))
                .isInstanceOf(IncompleteRequestException.class)
                .hasMessage("WishList id is a mandatory field");
    }

}
