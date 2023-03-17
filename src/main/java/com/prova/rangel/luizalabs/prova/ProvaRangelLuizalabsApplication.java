package com.prova.rangel.luizalabs.prova;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.prova.rangel.luizalabs.prova.infraestructure.database.repository.*;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses
	    = {
	    		WishListRepository.class
	    })
public class ProvaRangelLuizalabsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProvaRangelLuizalabsApplication.class, args);
	}

}
