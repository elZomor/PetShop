package com.assessment.PetShop.controller;

import com.assessment.PetShop.domain.Customer;
import com.assessment.PetShop.domain.Dog;
import com.assessment.PetShop.domain.Order;
import com.assessment.PetShop.domain.OrderItem;
import com.assessment.PetShop.exception.CustomerException;
import com.assessment.PetShop.exception.DogNotFoundException;
import com.assessment.PetShop.exception.InventoryException;
import com.assessment.PetShop.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderRepository orderRepository ;

    @Autowired
    InventoryRepository inventoryRepository ;

    @Autowired
    OrderItemRepository orderItemRepository ;

    @Autowired
    DogRepository dogRepository;

    @Autowired
    CustomerRepository customerRepository ;

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder (@RequestBody OrderItem orderItem) {
        orderItem.setDog(dogRepository.findByBreedAndSex(
                orderItem.getDog().getBreed() , orderItem.getDog().getSex())
                .orElseThrow(() -> new DogNotFoundException("orderItem.getDog().getBreed()")));

        orderItem.setCustomer(customerRepository.findByEmail(orderItem.getCustomer().getEmail())
                        .orElseGet(() -> customerRepository.save(new Customer(orderItem.getCustomer().getName()
                                , orderItem.getCustomer().getEmail()))));
        inventoryRepository.findByDog(orderItem.getDog()).map(inventory -> {
            if (inventory.getQuantity() == 0) throw new InventoryException("There is no quantity in the stock") ;
            inventory.setQuantity(inventory.getQuantity() - 1) ;
            return inventoryRepository.save(inventory);
        }).orElseThrow(() -> new InventoryException("This item is not found"));
        Order order = orderRepository.save(new Order(orderItem.getCustomer())) ;
        orderItem.setOrder(order);
        orderItemRepository.save(orderItem) ;
        return new ResponseEntity<>(order, HttpStatus.OK) ;
    }
}
