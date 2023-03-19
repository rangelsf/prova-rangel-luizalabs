package com.prova.rangel.luizalabs.prova.domain.usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.prova.rangel.luizalabs.prova.domain.response.FindIfProductIsOnWishListResponse;
import com.prova.rangel.luizalabs.prova.domain.response.FindWishListByIdResponse;
import com.prova.rangel.luizalabs.prova.domain.usecase.impl.FindIfProductIsOnWishListImpl;
import com.prova.rangel.luizalabs.prova.exception.IncompleteRequestException;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class FindIfProductIsOnWishListTest {

	FindIfProductIsOnWishList findIfProductIsOnWishList;

	@Mock
	private FindWishListById findWishListById;

	@BeforeEach
	void init(){
		findIfProductIsOnWishList = new FindIfProductIsOnWishListImpl(findWishListById);

	}

	@DisplayName("If the wish list and the product are not null or empty, and the product is on the wish list, should true")
	@Test
	void shouldReturnTrueWhenTheProductIsOnWishList(){
		FindWishListByIdResponse findWishListByIdResponse = new FindWishListByIdResponse();
		findWishListByIdResponse.setClientId("1");
		findWishListByIdResponse.setId("1");
		findWishListByIdResponse.setName("name");
		findWishListByIdResponse.setProductIdList(List.of("2"));

		Mockito.when(findWishListById.findWishListById("1")).thenReturn(findWishListByIdResponse);

		FindIfProductIsOnWishListResponse findIfProductIsOnWishListResponse = findIfProductIsOnWishList.findIfProductIsOnWishList("1", "2");

		Assertions.assertNotNull(findIfProductIsOnWishListResponse);
		Assertions.assertTrue(findIfProductIsOnWishListResponse.isExists());
	}

	@DisplayName("If the wish list and the product are not null or empty, and the wish list exists but not the product, should return false")
	@Test
	void shouldReturnFalseWhenTheProductIsNotOnWishList(){
		FindWishListByIdResponse findWishListByIdResponse = new FindWishListByIdResponse();
		findWishListByIdResponse.setClientId("1");
		findWishListByIdResponse.setId("1");
		findWishListByIdResponse.setName("name");
		findWishListByIdResponse.setProductIdList(List.of("3"));
		Mockito.when(findWishListById.findWishListById("1")).thenReturn(findWishListByIdResponse);

		FindIfProductIsOnWishListResponse findIfProductIsOnWishListResponse = findIfProductIsOnWishList.findIfProductIsOnWishList("1", "2");

		Assertions.assertNotNull(findIfProductIsOnWishListResponse);
		Assertions.assertFalse(findIfProductIsOnWishListResponse.isExists());
	}

	@DisplayName("If the wish list and the product are not null or empty, and the wish list exists and is empty, should return false")	
	@Test
	void shouldReturnFalseWhenWishListHasNoProducts(){
		
		FindWishListByIdResponse findWishListByIdResponse = new FindWishListByIdResponse();
		findWishListByIdResponse.setClientId("1");
		findWishListByIdResponse.setId("1");
		findWishListByIdResponse.setName("name");
		findWishListByIdResponse.setProductIdList(List.of());
		
		Mockito.when(findWishListById.findWishListById("1")).thenReturn(findWishListByIdResponse);

		FindIfProductIsOnWishListResponse findIfProductIsOnWishListResponse = findIfProductIsOnWishList.findIfProductIsOnWishList("1", "2");

		Assertions.assertNotNull(findIfProductIsOnWishListResponse);
		Assertions.assertFalse(findIfProductIsOnWishListResponse.isExists());
	}
	
	@DisplayName("If the wish list is null and the product exists, should throw exception")
	@Test
    void shouldThrowWhenWishListNull(){
        org.assertj.core.api.Assertions.assertThatThrownBy(
                () -> findIfProductIsOnWishList.findIfProductIsOnWishList(null, "2"))
                .isInstanceOf(IncompleteRequestException.class)
                .hasMessage("WishList id is a mandatory field");
    }
	
	@DisplayName("If the wish list exists and the product is null, should throw exception")
	@Test
    void shouldThrowWhenProducIsNull(){
        org.assertj.core.api.Assertions.assertThatThrownBy(
                () -> findIfProductIsOnWishList.findIfProductIsOnWishList("1", null))
                .isInstanceOf(IncompleteRequestException.class)
                .hasMessage("Product id is a mandatory field");
    }

}
