package com.prova.rangel.luizalabs.prova.infraestructure.database;


import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.List;

import com.prova.rangel.luizalabs.prova.AbstractContainerBaseTest;
import com.prova.rangel.luizalabs.prova.Integration;
import com.prova.rangel.luizalabs.prova.domain.entity.factory.WishListFactory;
import com.prova.rangel.luizalabs.prova.domain.entity.WishList;
import com.prova.rangel.luizalabs.prova.domain.entity.impl.WishListImpl;
import com.prova.rangel.luizalabs.prova.infraestructure.database.model.WishListModel;

@Testcontainers
@SpringBootTest
@Category(Integration.class)
class WishListDataServicesIntegrationTest extends AbstractContainerBaseTest {

	@Autowired
	private WishListDataServices wishListDataServices;

	@Autowired
	private WishListFactory wishListFactory;

	@Autowired
	private MongoTemplate mongoTemplate;


	@Test
	void shouldSave() {
		List<String> productList = new ArrayList<>();
		productList.add("product");
		WishList wishList = new WishListImpl(productList, "1", "name");

		String wishListId = "123456789";
		WishListModel dataModel = this.wishListDataServices.save(wishListId, wishList);

		Assertions.assertNotNull(dataModel);
	}

}
