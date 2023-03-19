package com.prova.rangel.luizalabs.prova.infraestructure.controller;

import com.prova.rangel.luizalabs.prova.AbstractContainerBaseTest;
import com.prova.rangel.luizalabs.prova.Integration;
import com.prova.rangel.luizalabs.prova.domain.request.AddProductOnWishListRequest;
import com.prova.rangel.luizalabs.prova.domain.response.AddProductOnWishListResponse;
import com.prova.rangel.luizalabs.prova.domain.response.FindAllProductsOnWishListResponse;
import com.prova.rangel.luizalabs.prova.domain.response.FindIfProductIsOnWishListResponse;
import com.prova.rangel.luizalabs.prova.domain.response.FindWishListByIdResponse;
import com.prova.rangel.luizalabs.prova.exception.BusinessRuleException;
import com.prova.rangel.luizalabs.prova.exception.DataNotFoundException;
import com.prova.rangel.luizalabs.prova.exception.IncompleteRequestException;
import com.prova.rangel.luizalabs.prova.domain.request.CreateWishListRequest;
import com.prova.rangel.luizalabs.prova.domain.response.CreateWishListResponse;

import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
@Category(Integration.class)
public class WishListControllerTest extends AbstractContainerBaseTest{

	@Autowired
	private WishListController wishListController;

	@Autowired
	private MongoTemplate mongoTemplate;

	@BeforeEach
	void init() {
		startWishlist(mongoTemplate);
		startProduct(mongoTemplate);
		startClient(mongoTemplate);
	}

	@AfterEach
	void end() {
		dropWishlist(mongoTemplate);
		dropProduct(mongoTemplate);
		dropClient(mongoTemplate);
	}


	@DisplayName("Product does not exist, should throw exception")
	@Test
	void shouldThrowExceltionWhenProductDoesNotExist() {
		AddProductOnWishListRequest addProductOnWishListRequest = new AddProductOnWishListRequest();
		addProductOnWishListRequest.setClientId("c1");
		addProductOnWishListRequest.setProductId("p23");
		addProductOnWishListRequest.setWishListId("w1");

		org.assertj.core.api.Assertions.assertThatThrownBy(
				() -> wishListController.addProductOnWishList(addProductOnWishListRequest))
		.isInstanceOf(DataNotFoundException.class)
		.hasMessage("Product not found");

	}

	@Test
	@DisplayName("If the wish list is full, should throw")
	void shouldThrowWhenWishListFull() {

		AddProductOnWishListRequest addProductOnWishListRequest = new AddProductOnWishListRequest();
		addProductOnWishListRequest.setClientId("c1");
		addProductOnWishListRequest.setProductId("p21");
		addProductOnWishListRequest.setWishListId("w1");

		org.assertj.core.api.Assertions.assertThatThrownBy(
				() -> wishListController.addProductOnWishList(addProductOnWishListRequest))
		.isInstanceOf(BusinessRuleException.class)
		.hasMessage("Wish list is full");

	}

	@Test
	@DisplayName("When the wish list is not full, should add product")
	void shouldAddProductOnWishListWhenNotFull() {
		AddProductOnWishListRequest addProductOnWishListRequest = new AddProductOnWishListRequest();
		addProductOnWishListRequest.setClientId("c1");
		addProductOnWishListRequest.setProductId("p1");
		addProductOnWishListRequest.setWishListId("w2");

		AddProductOnWishListResponse addProductOnWishListResponse = wishListController.addProductOnWishList(addProductOnWishListRequest);

		Assertions.assertNotNull(addProductOnWishListResponse);
		Assertions.assertNotNull(addProductOnWishListResponse.getClientId());
		Assertions.assertNotNull(addProductOnWishListResponse.getWishListId());
		Assertions.assertNotNull(addProductOnWishListResponse.getProductIdList());

		Assertions.assertEquals(1, addProductOnWishListResponse.getProductIdList().size());

	}

	@DisplayName("When the wish list is empty, should throw")
	@Test
	void shouldThrowWhenWishListIsEmpty() {
		org.assertj.core.api.Assertions.assertThatThrownBy(
				() -> wishListController.findAllProductsOnWishList(""))
		.isInstanceOf(IncompleteRequestException.class)
		.hasMessage("WishList id is a mandatory field");
	}

	@DisplayName("If the wish list exists but has no products, should throw")
	@Test
	void shouldThrowWhenWishListHasNoProducts() {
		org.assertj.core.api.Assertions.assertThatThrownBy(
				() -> wishListController.findAllProductsOnWishList("w2"))
		.isInstanceOf(DataNotFoundException.class)
		.hasMessage("No products on this wish list"); 
	}

	@DisplayName("If the wish list exists and has products, should return products")
	@Test
	void shouldFindProducts() {
		FindAllProductsOnWishListResponse findAllProductsOnWishListResponse = wishListController.findAllProductsOnWishList("w1");

		Assertions.assertNotNull(findAllProductsOnWishListResponse);
		Assertions.assertNotNull(findAllProductsOnWishListResponse.getProductList());
		Assertions.assertEquals(20, findAllProductsOnWishListResponse.getProductList().size());

	}

	@DisplayName("Dado que a wishlist existe mas tem produtos, retornar a lista de produtos.")
	@Test
	void shouldCreateWishList() {

		CreateWishListRequest createWishListRequest = new CreateWishListRequest();
		createWishListRequest.setName("list");
		createWishListRequest.setClientId("c2");

		CreateWishListResponse createWishListResponse = wishListController.createWishList(createWishListRequest);

		Assertions.assertNotNull(createWishListResponse);
		Assertions.assertEquals("c2", createWishListResponse.getClientId());
		Assertions.assertEquals("list", createWishListResponse.getName());


	}


	@DisplayName("When the wish list exists and the product is on the list, should return true")
	@Test
	void shouldReturnTrueWhenProductOnWishList() {
		FindIfProductIsOnWishListResponse response = wishListController.findIfProductIsOnWishList("w1", "p1");

		Assertions.assertNotNull(response);
		Assertions.assertTrue(response.isExists());

	}

	@DisplayName("If the wish list exists, should return wish list.")
	@Test
	void shouldFindWishListById() {
		FindWishListByIdResponse response = wishListController.findWishListById("w2");

		Assertions.assertNotNull(response);
		Assertions.assertEquals("Empty List", response.getName());

	}

	@DisplayName("When the wish list exists and has the product, should remove the product from wish list")
	@Test
	void shouldRemoveProductFromWishList() {
		wishListController.deleteProductByIdFromWishList("w1", "p1");

		FindWishListByIdResponse findWishListByIdResponse = wishListController.findWishListById("w1");

		Assertions.assertNotNull(findWishListByIdResponse);
		Assertions.assertEquals(19, findWishListByIdResponse.getProductIdList().size());

	}
}
