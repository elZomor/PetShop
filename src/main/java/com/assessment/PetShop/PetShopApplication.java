package com.assessment.PetShop;

import com.assessment.PetShop.controller.DogController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PetShopApplication {

	@Autowired
	private DogController dogController ;

	public static void main(String[] args) {
		SpringApplication.run(PetShopApplication.class, args);
	}

}
