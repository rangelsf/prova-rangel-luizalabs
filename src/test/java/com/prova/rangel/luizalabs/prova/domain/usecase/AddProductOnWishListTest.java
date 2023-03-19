package com.prova.rangel.luizalabs.prova.domain.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;

import com.prova.rangel.luizalabs.prova.domain.entity.factory.WishListFactory;
import com.prova.rangel.luizalabs.prova.domain.entity.factory.impl.WishListFactoryImpl;
import com.prova.rangel.luizalabs.prova.domain.usecase.impl.AddProductOnWishListImpl;
import com.prova.rangel.luizalabs.prova.infraestructure.database.WishListDataServices;
import com.prova.rangel.luizalabs.prova.domain.request.AddProductOnWishListRequest;
import com.prova.rangel.luizalabs.prova.domain.response.AddProductOnWishListResponse;
import com.prova.rangel.luizalabs.prova.domain.response.FindIfProductIsOnWishListResponse;
import com.prova.rangel.luizalabs.prova.infraestructure.database.model.WishListModel;
import com.prova.rangel.luizalabs.prova.exception.BusinessRuleException;
import com.prova.rangel.luizalabs.prova.exception.DataNotFoundException;

@ExtendWith(MockitoExtension.class)
public class AddProductOnWishListTest {

	private WishListFactory wishListFactory = new WishListFactoryImpl();

	AddProductOnWishList addProductOnWishList;

	@Mock
	private WishListDataServices wishListDataServices;

	@Mock
	FindIfProductExistsById findIfProductExistsById;

	@Mock
	FindIfProductIsOnWishList findIfProductIsOnWishList;


	@BeforeEach
	void init(){
		addProductOnWishList = new AddProductOnWishListImpl(wishListDataServices, wishListFactory, findIfProductIsOnWishList,findIfProductExistsById);
	}

	@DisplayName("If wish list does not exist, should throw exception")
	@Test
	void shouldNotAddProductOnWishListWhenWishListDoesNotExist(){
		AddProductOnWishListRequest addProductOnWishListRequest = new AddProductOnWishListRequest();
		addProductOnWishListRequest.setWishListId("1");
		addProductOnWishListRequest.setClientId("2");
		addProductOnWishListRequest.setProductId("3");
		List<String> products = new ArrayList<>();
		products.add("4");
		products.add("5");
		WishListModel wishlistDataModel = new WishListModel();
		wishlistDataModel.setWishListId(addProductOnWishListRequest.getWishListId());
		wishlistDataModel.setName("lista");
		wishlistDataModel.setClientId(addProductOnWishListRequest.getClientId());
		wishlistDataModel.setProductIdList(products);

		Mockito.when(wishListDataServices.findWishListByClientIdAndId(addProductOnWishListRequest.getClientId(),
				addProductOnWishListRequest.getWishListId())).thenReturn(Optional.empty());

		org.assertj.core.api.Assertions.assertThatThrownBy(
				() -> addProductOnWishList.add(addProductOnWishListRequest))
		.isInstanceOf(DataNotFoundException.class)
		.hasMessage("Wish list not found");
	}

	@DisplayName("If product does not exist, should throw exception")
	@Test
	void shouldNotAddIfProductNotExist(){
		AddProductOnWishListRequest addProductOnWishListRequest = new AddProductOnWishListRequest();
		addProductOnWishListRequest.setWishListId("1");
		addProductOnWishListRequest.setClientId("2");
		addProductOnWishListRequest.setProductId("3");
		
		List<String> productList = new ArrayList<>();
		WishListModel wishListModel = new WishListModel();		
		wishListModel.setWishListId(addProductOnWishListRequest.getWishListId());
		wishListModel.setName("list");
		wishListModel.setClientId(addProductOnWishListRequest.getClientId());
		wishListModel.setProductIdList(productList);

		Mockito.when(wishListDataServices.findWishListByClientIdAndId(addProductOnWishListRequest.getClientId(),
				addProductOnWishListRequest.getWishListId())).thenReturn(Optional.of(wishListModel));
		Mockito.when(findIfProductExistsById.findIfProductExistsById("3")).thenReturn(false);

		org.assertj.core.api.Assertions.assertThatThrownBy(
				() -> addProductOnWishList.add(addProductOnWishListRequest))
		.isInstanceOf(DataNotFoundException.class)
		.hasMessage("Product not found");
	}
	
	
	@DisplayName("If wish list and product exist, and wish list has 20 products, should not add new product, and should throw exception")
	@Test
    void shouldNotAddProductIfWishListFull(){
		AddProductOnWishListRequest wishlistRequest = new AddProductOnWishListRequest();
		wishlistRequest.setWishListId("1");
		wishlistRequest.setClientId("2");
		wishlistRequest.setProductId("21");
		
        List<String> productList = IntStream.range(0, 20)
                .mapToObj(String::valueOf).collect(Collectors.toList());
        WishListModel wishListModel = new WishListModel();
        wishListModel.setWishListId(wishlistRequest.getWishListId());
        wishListModel.setName("list");
        wishListModel.setClientId(wishlistRequest.getClientId());
        wishListModel.setProductIdList(productList);

        Mockito.when(wishListDataServices.findWishListByClientIdAndId(wishlistRequest.getClientId(),
                wishlistRequest.getWishListId())).thenReturn(Optional.of(wishListModel));
        Mockito.when(findIfProductExistsById.findIfProductExistsById("21")).thenReturn(true);
        
        FindIfProductIsOnWishListResponse findIfProductIsOnWishListResponse = new FindIfProductIsOnWishListResponse(false);
        Mockito.when(findIfProductIsOnWishList.findIfProductIsOnWishList(wishlistRequest.getWishListId(), "21")).thenReturn(findIfProductIsOnWishListResponse);
    	

        org.assertj.core.api.Assertions.assertThatThrownBy(
                () -> addProductOnWishList.add(wishlistRequest))
                .isInstanceOf(BusinessRuleException.class)
                .hasMessage("Wish list is full");
  
    }

}
