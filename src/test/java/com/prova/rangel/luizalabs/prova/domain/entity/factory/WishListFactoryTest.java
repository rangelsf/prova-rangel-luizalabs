package com.prova.rangel.luizalabs.prova.domain.entity.factory;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import com.prova.rangel.luizalabs.prova.domain.entity.factory.WishListFactory;
import com.prova.rangel.luizalabs.prova.domain.entity.factory.impl.WishListFactoryImpl;
import com.prova.rangel.luizalabs.prova.domain.entity.WishList;

public class WishListFactoryTest {

	WishListFactory wishlistFactory = new WishListFactoryImpl();

	@DisplayName("Simple test to create a WishList object")
	@Test
	void shouldCreate(){
		WishList wishList = wishlistFactory.createNewWishList("c1",List.of("p4", "p5"),"List");

		Assertions.assertNotNull(wishList);
		Assertions.assertNotNull(wishList.getProductIdList());
		Assertions.assertEquals("c1", wishList.getClientId());
		Assertions.assertEquals(2, wishList.getProductIdList().size());
		Assertions.assertEquals("List", wishList.getName());
		org.assertj.core.api.Assertions.assertThat(wishList.getProductIdList()).contains("p4", "p5");
	}
}
