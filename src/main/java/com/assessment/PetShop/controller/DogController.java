package com.assessment.PetShop.controller;

import com.assessment.PetShop.domain.Dog;
import com.assessment.PetShop.repo.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    @PatchMapping
    @RequestMapping("/{id}")
    public Dog updateDogPartially (@PathVariable(name = "id") Integer id , @RequestBody Map<String, Object> changes) {
        Dog dog = dogRepository.findById(id).orElseThrow(() -> new NoSuchElementException("This ID does not exist")) ;
        changes.forEach(
                (change,value) -> {
                    switch (change) {
                        case "breed" : dog.setBreed((String) value); break ;
                        case "sex" : dog.setSex((char) value);
                    }
                }
        );
        return dogRepository.save(dog) ;
    }

    @PutMapping
    @RequestMapping("/{id}")
    public Dog updateDog (@PathVariable(name = "id") Integer id , @RequestBody Dog newDog) {
        return dogRepository.findById(id).map(
                dog -> {
                    dog.setSex(newDog.getSex());
                    dog.setBreed(newDog.getBreed());
                    return dogRepository.save(dog);
                }
                )
                .orElseThrow(() -> new NoSuchElementException("This ID does not exist"));
    }

    @DeleteMapping
    @RequestMapping("/{id}")
    public void deleteDog (@PathVariable(name = "id") Integer id) {
        dogRepository.deleteById(id);
    }
}
