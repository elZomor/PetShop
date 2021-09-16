package com.assessment.PetShop.controller;

import com.assessment.PetShop.domain.Dog;
import com.assessment.PetShop.repo.DogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DogController.class)
class DogControllerTest {
    @Autowired
    MockMvc mockMvc ;
    @Autowired
    ObjectMapper mapper ;

    @MockBean
    DogRepository dogRepository;

    Dog dog_1 = new Dog("balady" , 'm');
    Dog dog_2 = new Dog("wolf" , 'f');
    Dog dog_3 = new Dog("wolf" , 'm');

    @Test
    void getAllDogs() throws Exception{
        List<Dog> dogs = new ArrayList<>(Arrays.asList(dog_1,dog_2,dog_3));
        Mockito.when(dogRepository.findAll()).thenReturn(dogs);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/dogs")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(3)))
                .andExpect(jsonPath("$[2].breed", Matchers.is("wolf")));
    }

//    @Test
//    void getSingleDog() {
//    }
//
//    @Test
//    void getDogsByBreed() {
//    }
//
//    @Test
//    void createDog() {
//    }
//
//    @Test
//    void updateDogPartially() {
//    }
//
//    @Test
//    void updateDog() {
//    }
//
//    @Test
//    void deleteDog() {
//    }
}