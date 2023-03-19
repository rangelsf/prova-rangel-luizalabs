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

import com.prova.rangel.luizalabs.prova.domain.usecase.impl.FindIfClientExistsByIdImpl;
import com.prova.rangel.luizalabs.prova.infraestructure.database.ClientDataServices;
import com.prova.rangel.luizalabs.prova.infraestructure.database.model.ClientModel;

@ExtendWith(MockitoExtension.class)
class FindIfClientExistsByIdTest {

	FindIfClientExistsById findIfClientExistsById;

	@Mock
	private ClientDataServices clientDataServices;

	@BeforeEach
	void init(){
		findIfClientExistsById = new FindIfClientExistsByIdImpl(clientDataServices);
	}


	@DisplayName("If the client Id is not null or empty and the client id exists, should return true")
	@Test
	void shouldReturnTrueWhenClientIdNotNullAndExists(){
		ClientModel clientModel = new ClientModel();
		clientModel.setClientId("1");
		clientModel.setName("name");

		Mockito.when(clientDataServices.findByClientId("1")).thenReturn(Optional.of(clientModel));


		Assertions.assertTrue(findIfClientExistsById.findIfClientExistsById("1"));
	}

	@DisplayName("If the client id is not null or blank and the client id does not exist, should return false")
	@Test
	void shouldReturnFalseWhenClientIdNotNullAndDontExists(){
		Mockito.when(clientDataServices.findByClientId("1")).thenReturn(Optional.empty());

		Assertions.assertFalse(findIfClientExistsById.findIfClientExistsById("1"));
	}



}
