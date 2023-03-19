package com.prova.rangel.luizalabs.prova.domain.usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.prova.rangel.luizalabs.prova.domain.usecase.impl.FindIfProductExistsByIdImpl;
import com.prova.rangel.luizalabs.prova.infraestructure.database.ProductDataServices;
import com.prova.rangel.luizalabs.prova.infraestructure.database.model.ProductModel;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class FindIfProductExistsByIdTest {

	FindIfProductExistsById findIfProductExistsById;

	@Mock
	private ProductDataServices productDataServices;

	@BeforeEach
	void init(){
		findIfProductExistsById = new FindIfProductExistsByIdImpl(productDataServices);
	}


	@DisplayName("If product exists, should return true")
	@Test
	void shouldReturnTrueIfProductExists(){
		ProductModel productModel = new ProductModel();
		productModel.setName("name");
		productModel.setProductId("1");

		Mockito.when(productDataServices.findByProductId("1")).thenReturn(Optional.of(productModel));

		Assertions.assertTrue(findIfProductExistsById.findIfProductExistsById("1"));
	}

	@DisplayName("If the product does not exist should return false")
	@Test
	void shoulReturnFalseWhenProductDoesNotExist(){

		Mockito.when(productDataServices.findByProductId("1")).thenReturn(Optional.empty());

		Assertions.assertFalse(findIfProductExistsById.findIfProductExistsById("1"));
	}


}
