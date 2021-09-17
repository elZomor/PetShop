package com.assessment.PetShop.controller;

import com.assessment.PetShop.domain.*;
import com.assessment.PetShop.repo.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.in;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {
    @MockBean
    DogRepository dogRepository ;

    @MockBean
    CustomerRepository customerRepository ;

    @MockBean
    InventoryRepository inventoryRepository ;

    @MockBean
    OrderRepository orderRepository;

    @MockBean
    OrderItemRepository orderItemRepository ;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    MockMvc mockMvc ;

    @Test
    void createOrder() throws Exception {
        Dog dog = new Dog("balady" , 'f');
        dog.setId(1);
        Customer customer = new Customer("mohamed","123@email.com");
        customer.setId(1);
        Order order = new Order(customer) ;
        order.setId(1);
        Inventory inventory = new Inventory(dog,10);
        inventory.setId(1);
        Inventory newInventory = new Inventory(dog,9);
        newInventory.setId(1);
        OrderItem orderItem = new OrderItemBuilder()
                .setDog(dog)
                .setCurrency("EGP")
                .setPrice(200)
                .setOrder(order)
                .setCustomer(customer)
                .createOrderItem();
        orderItem.setId(1);
        Mockito.when(dogRepository.findByBreedAndSex(dog.getBreed(),dog.getSex()))
                .thenReturn(java.util.Optional.of(dog));
        Mockito.when(customerRepository.findByEmail(customer.getEmail()))
                .thenReturn(java.util.Optional.of(customer));
        Mockito.when(inventoryRepository.findByDog(dog))
                .thenReturn(java.util.Optional.of(inventory));
        Mockito.any(Customer.class);
        Mockito.when(customerRepository.save(customer)).thenReturn(customer);
        Mockito.any(Inventory.class);
        Mockito.when(inventoryRepository.save(newInventory)).thenReturn(newInventory);
        Mockito.any(Order.class);
        Mockito.when(orderRepository.save(order)).thenReturn(order);
        Mockito.any(OrderItem.class);
        Mockito.when(orderItemRepository.save(orderItem)).thenReturn(orderItem);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/orders/create")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(orderItem));
        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.customer.name", Matchers.is("mohamed")));
    }
}