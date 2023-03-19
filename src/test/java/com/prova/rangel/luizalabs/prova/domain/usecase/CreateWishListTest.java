package com.prova.rangel.luizalabs.prova.domain.usecase;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.prova.rangel.luizalabs.prova.domain.entity.factory.WishListFactory;
import com.prova.rangel.luizalabs.prova.domain.entity.factory.impl.WishListFactoryImpl;
import com.prova.rangel.luizalabs.prova.domain.response.AddProductOnWishListResponse;
import com.prova.rangel.luizalabs.prova.domain.response.CreateWishListResponse;
import com.prova.rangel.luizalabs.prova.domain.usecase.impl.CreateWishListImpl;
import com.prova.rangel.luizalabs.prova.infraestructure.database.WishListDataServices;
import com.prova.rangel.luizalabs.prova.infraestructure.database.model.WishListModel;
import com.prova.rangel.luizalabs.prova.exception.DataNotFoundException;
import com.prova.rangel.luizalabs.prova.exception.IncompleteRequestException;

@ExtendWith(MockitoExtension.class)
public class CreateWishListTest {

	CreateWishList createWishList;
	
	private WishListFactory wishListFactory = new WishListFactoryImpl();
	
	@Mock
	private WishListDataServices wishListDataServices;
	
	@Mock
	private FindIfClientExistsById findIfClientExistsById;
	
	@BeforeEach
	void init() {
		createWishList = new CreateWishListImpl(wishListFactory, wishListDataServices, findIfClientExistsById);
	}
	
	@Test
    @DisplayName("When the client id does not exist, an exception must be thrown")
    void shouldNotCreateWishListWhenClientIdDoesNotExist(){
        Mockito.when(findIfClientExistsById.findIfClientExistsById("99999")).thenReturn(false);
        org.assertj.core.api.Assertions.assertThatThrownBy(
                () -> createWishList.create("99999", "list"))
                .isInstanceOf(DataNotFoundException.class)
                .hasMessage("Client not found");
    }
	
	
	@Test
    @DisplayName("Name and client id should not be null or empty, and the client exists. Should create a new wish list.")
    void shouldCreateNewWishList(){
        WishListModel wishListModel = new WishListModel();
        wishListModel.setClientId("c1");
        wishListModel.setName("List");
        wishListModel.setProductIdList(List.of());
        wishListModel.setWishListId("w1");

        Mockito.when(wishListDataServices.save(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(wishListModel);
        Mockito.when(findIfClientExistsById.findIfClientExistsById("c1")).thenReturn(true);

        CreateWishListResponse addProductOnWishListResponse = createWishList.create("c1","List");

        Mockito.verify(wishListDataServices).save(ArgumentMatchers.any(), ArgumentMatchers.any());
        Assertions.assertNotNull(addProductOnWishListResponse);
    }
	
	@Test
    @DisplayName("When the client id is blank, an exception must be thrown")
    void shouldNotCreateWishListWhenClientIdIsBlank(){
        org.assertj.core.api.Assertions.assertThatThrownBy(
                () -> createWishList.create(" ", "list"))
                .isInstanceOf(IncompleteRequestException.class)
                .hasMessage("Client id is a mandatory field");
    }

	@Test
    @DisplayName("When the name is null, an exception must be thrown")
    void shouldNotCreateWishListWhenNameIsNull(){
        org.assertj.core.api.Assertions.assertThatThrownBy(
                () -> createWishList.create("c1",null))
                .isInstanceOf(IncompleteRequestException.class)
                .hasMessage("name is a mandatory field");
    }
	
	@Test
    @DisplayName("When the name is blank, an exception must be thrown")
    void shouldNotCreateWishListWhenNameIsBlank(){
        org.assertj.core.api.Assertions.assertThatThrownBy(
                () -> createWishList.create("c1","  "))
                .isInstanceOf(IncompleteRequestException.class)
                .hasMessage("name is a mandatory field");
    }
	
	@Test
    @DisplayName("When the client id is null, an exception must be thrown")
    void shouldNotCreateWishListWhenClientIdIsNull(){
        org.assertj.core.api.Assertions.assertThatThrownBy(
                () -> createWishList.create(null, "list"))
                .isInstanceOf(IncompleteRequestException.class)
                .hasMessage("Client id is a mandatory field");
    }
	
	
}
