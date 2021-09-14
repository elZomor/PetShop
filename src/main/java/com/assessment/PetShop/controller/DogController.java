package com.assessment.PetShop.controller;

import com.assessment.PetShop.domain.Dog;
import com.assessment.PetShop.repo.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dogs")
public class DogController {
    private DogRepository dogRepository ;

    @Autowired
    public DogController(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @GetMapping
    public List<Dog> getAllDogs() {
        return dogRepository.findAll();
    }

    @GetMapping
    @RequestMapping("/{breed}")
    public List<Dog> getDogsByBreed(@PathVariable(name = "breed") String breed) {
        return dogRepository.findByBreed(breed);
    }

    @PostMapping
    public Dog createDog(@RequestBody Dog dog) {
        return dogRepository.save(dog) ;
    }
}
