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
public class PetShopApplication implements CommandLineRunner {

	@Autowired
	private DogController dogController ;

	@Autowired
	private DogRepository dogRepository;

	@Autowired
	private InventoryRepository inventoryRepository ;

	@Autowired
	private OrderRepository orderRepository ;

	@Autowired
	private OrderItemRepository orderItemRepository ;

	@Autowired
	private CustomerRepository customerRepository ;

	public static void main(String[] args) {
		SpringApplication.run(PetShopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Dog dogOne = new Dog("wolf", 'f');
		Dog dogTwo = new Dog("labrador" , 'f');
		Dog dogThree = new Dog("labrador" , 'm');
		List<Dog> dogList = new ArrayList<>(List.of(dogOne,dogTwo,dogThree));
		List<Inventory> inventoryList = new ArrayList<>(List.of(
				new Inventory(dogOne,9),
				new Inventory(dogTwo,1),
				new Inventory(dogThree,0)
		));
		dogRepository.saveAll(dogList);
		inventoryRepository.saveAll(inventoryList);
		customerRepository.save(new Customer("Mohamed" , "123@e.com"));



	}
}
