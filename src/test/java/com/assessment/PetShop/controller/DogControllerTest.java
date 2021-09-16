package com.assessment.PetShop.controller;

import com.assessment.PetShop.domain.Dog;
import com.assessment.PetShop.exception.DogNotFoundException;
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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

    @Test
    void getSingleDog() throws Exception{
        List<Dog> dogs = new ArrayList<>(Arrays.asList(dog_1,dog_2,dog_3));
        Mockito.when(dogRepository.findById(dog_1.getId())).thenReturn(
                java.util.Optional.of(dog_1));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/dogs/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.breed", Matchers.is("balady")));
    }

    @Test
    void getDogsByBreed() throws Exception{
        List<Dog> dogs = new ArrayList<>(Arrays.asList(dog_1,dog_2,dog_3));
        Mockito.when(dogRepository.findByBreed(dog_1.getBreed())).thenReturn(
                java.util.List.of(dog_1));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/dogs/breeds/balady")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.sex", Matchers.is('m')));
    }

    @Test
    void createDog() throws Exception {
        Dog newDog = new Dog("labrador",'f');
        Mockito.when(dogRepository.save(newDog)).thenReturn(newDog);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/dogs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(newDog));
        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.breed", Matchers.is("labrador")));
    }


    @Test
    void updateDogPartially() throws Exception{
        Dog updatedDog = new Dog();
        updatedDog.setBreed("labrador");
        Mockito.when(dogRepository.findById(dog_3.getId())).thenReturn(
                java.util.Optional.of(dog_3));
        Mockito.when(dogRepository.save(updatedDog)).thenReturn(updatedDog);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.patch("/dogs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(updatedDog));
        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.breed", Matchers.is("labrador")))
                .andExpect(jsonPath("$.sex",Matchers.is('m')));
    }

    @Test
    void updateDog() throws Exception{
        Dog updatedDog = new Dog();
        updatedDog.setBreed("labrador");
        updatedDog.setSex('f');
        Mockito.when(dogRepository.findById(dog_3.getId())).thenReturn(
                java.util.Optional.of(dog_3));
        Mockito.when(dogRepository.save(updatedDog)).thenReturn(updatedDog);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/dogs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(updatedDog));
        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.breed", Matchers.is("labrador")))
                .andExpect(jsonPath("$.sex",Matchers.is('f')));
    }

    @Test
    void updateDog_nullId() throws Exception{
        Dog updatedDog = new Dog();
        updatedDog.setBreed("labrador");
        updatedDog.setSex('f');
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/dogs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(updatedDog));
        mockMvc.perform(mockRequest)
                .andExpect(status().isNotFound())
                .andExpect(result ->
                        assertTrue(result.getResolvedException() instanceof DogNotFoundException))
                .andExpect(result ->
                        assertEquals("Dog not found" ,result.getResolvedException().getMessage()));
    }

    @Test
    void deleteDog() throws Exception {
        Mockito.when(dogRepository.findById(dog_3.getId())).thenReturn(
                java.util.Optional.of(dog_3));
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/dogs/3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteDog_notFound() throws Exception {
        Mockito.when(dogRepository.findById(23)).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/dogs/23")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result ->
                        assertTrue(result.getResolvedException() instanceof DogNotFoundException))
                .andExpect(result ->
                        assertEquals("Dog not found" ,result.getResolvedException().getMessage()));

    }
}