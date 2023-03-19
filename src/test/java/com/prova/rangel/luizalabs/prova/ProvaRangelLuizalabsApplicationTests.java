package com.prova.rangel.luizalabs.prova;

import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;


@SpringBootTest
@Category(Integration.class)
class ProvaRangelLuizalabsApplicationTests {
	
	@Test
	void contextLoads() {
		Assertions.assertNotNull(applicationContext);
	}


	@Autowired
	ApplicationContext applicationContext;

}
