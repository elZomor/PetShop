package com.assessment.PetShop;

import com.assessment.PetShop.controller.DogController;
import com.assessment.PetShop.domain.Customer;
import com.assessment.PetShop.domain.Dog;
import com.assessment.PetShop.domain.Inventory;
import com.assessment.PetShop.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@SpringBootApplication
public class PetShopApplication  {

	public static void main(String[] args) {
		SpringApplication.run(PetShopApplication.class, args);
	}

}
