package com.assessment.PetShop.controller;

import com.assessment.PetShop.domain.Dog;
import com.assessment.PetShop.exception.DogNotFoundException;
import com.assessment.PetShop.repo.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/dogs")
public class DogController {
    final private DogRepository dogRepository ;

    public DogController(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @GetMapping
    public ResponseEntity<List<Dog>> getAllDogs() {
        List<Dog> dogList = dogRepository.findAll() ;
        if (dogList.isEmpty()) throw new DogNotFoundException() ;
        return new ResponseEntity<>(dogList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dog> getSingleDog(@PathVariable(name = "id") Integer id) {
        return dogRepository.findById(id).map(dog -> new ResponseEntity<>(dog,HttpStatus.OK))
                .orElseThrow(() -> new DogNotFoundException(id));
    }

    @GetMapping ("/breeds/{breed}")
    public ResponseEntity<List<Dog>> getDogsByBreed(@PathVariable(name = "breed") String breed) {
        List<Dog> dogList = dogRepository.findByBreed(breed);
        if (dogList.isEmpty()) throw new DogNotFoundException(breed) ;
        return new ResponseEntity<>(dogList,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Dog> createDog(@RequestBody @Valid Dog dog) {
        return new ResponseEntity<>(dogRepository.save(dog),HttpStatus.CREATED) ;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Dog> updateDogPartially (@PathVariable(name = "id") Integer id , @RequestBody Map<String, Object> changes) {
        Dog dog = dogRepository.findById(id)
                .orElseThrow(() -> new DogNotFoundException(id)) ;
        changes.forEach(
                (change,value) -> {
                    switch (change) {
                        case "breed" : dog.setBreed((String) value); break ;
                        case "sex" : dog.setSex((char) value);
                    }
                }
        );
        return new ResponseEntity<>(dogRepository.save(dog),HttpStatus.OK) ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dog> updateDog (@PathVariable(name = "id") Integer id , @RequestBody Dog newDog) {
        return dogRepository.findById(id).map(
                    dog -> {
                        dog.setSex(newDog.getSex());
                        dog.setBreed(newDog.getBreed());
                        return new ResponseEntity<>(dogRepository.save(dog),HttpStatus.OK);
                    }
                )
                .orElseThrow(() -> new DogNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Dog> deleteDog (@PathVariable(name = "id") Integer id) {
        dogRepository.findById(id).orElseThrow( () -> new DogNotFoundException(id));
        dogRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
