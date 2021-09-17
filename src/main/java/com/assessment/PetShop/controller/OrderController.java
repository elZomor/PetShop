package com.assessment.PetShop.controller;

import com.assessment.PetShop.domain.Order;
import com.assessment.PetShop.domain.OrderItem;
import com.assessment.PetShop.exception.InventoryException;
import com.assessment.PetShop.repo.InventoryRepository;
import com.assessment.PetShop.repo.OrderItemRepository;
import com.assessment.PetShop.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderRepository orderRepository ;

    @Autowired
    InventoryRepository inventoryRepository ;

    @Autowired
    OrderItemRepository orderItemRepository ;

    @PostMapping("/create")
    public Order createOrder (@RequestBody OrderItem orderItem) {
        inventoryRepository.findByDog(orderItem.getDog()).map(inventory -> {
            if (inventory.getQuantity() == 0) throw new InventoryException() ;
            inventory.setQuantity(inventory.getQuantity() - 1) ;
            return inventoryRepository.save(inventory);
        }).orElseThrow(InventoryException::new);
        Order order = orderRepository.save(new Order(orderItem.getCustomer())) ;
        orderItem.setOrder(order);
        orderItemRepository.save(orderItem) ;
        return order ;
    }
}
