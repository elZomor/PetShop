package com.assessment.PetShop.controller;

import com.assessment.PetShop.domain.Dog;
import com.assessment.PetShop.domain.Inventory;
import com.assessment.PetShop.exception.DogNotFoundException;
import com.assessment.PetShop.exception.InventoryException;
import com.assessment.PetShop.repo.DogRepository;
import com.assessment.PetShop.repo.InventoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    final private InventoryRepository inventoryRepository ;
    final private DogRepository dogRepository ;
    public InventoryController(InventoryRepository inventoryRepository, DogRepository dogRepository) {
        this.inventoryRepository = inventoryRepository;
        this.dogRepository = dogRepository ;
    }

    @GetMapping
    public ResponseEntity<List<Inventory>> getAllInventory() {
        List<Inventory> inventoryList = inventoryRepository.findAll() ;
        if (inventoryList.isEmpty()) throw new InventoryException("There is no data in the inventory") ;
        return new ResponseEntity<>(inventoryList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Inventory> createNewInventory(@RequestBody @Valid Inventory inventory) {
        Dog inventoryDog = inventory.getDog();
        Dog dog = dogRepository.findByBreedAndSex(inventoryDog.getBreed(),inventoryDog.getSex())
                .orElseGet(() -> dogRepository.save(inventoryDog));
        inventory.setDog(dog);
        return new ResponseEntity<>(inventoryRepository.save(inventory),HttpStatus.CREATED) ;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Inventory> updateDogPartially (@PathVariable(name = "id") Integer id , @RequestBody Map<String, Object> changes) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new InventoryException("This ID: " + id + " does not exist")) ;
        changes.forEach(
                (change,value) -> {
                    if (value instanceof LinkedHashMap) {
                        ((LinkedHashMap<String, Object>) value).forEach(
                                (key,val) -> {
                                    switch (key) {
                                        case "breed" : if (!Objects.isNull(value)) inventory.getDog().setBreed((String) val); break ;
                                        case "sex" :if (!Objects.isNull(value)) inventory.getDog().setSex(val.toString().toCharArray()[0]) ; break ;
                                        case "id" : inventory.getDog().setId(inventory.getDog().getId()); break ;
                                    }
                                }
                        );
                    } else {
                        if ("quantity".equals(change)) {
                            if (!Objects.isNull(value)) inventory.setQuantity((Integer) value);
                        }
                    }
//
                }
        );
        return new ResponseEntity<>(inventoryRepository.save(inventory),HttpStatus.OK) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Dog> deleteDog (@PathVariable(name = "id") Integer id) {
        inventoryRepository.findById(id).orElseThrow( () -> new InventoryException("This ID: " + id + " does not exist"));
        inventoryRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
