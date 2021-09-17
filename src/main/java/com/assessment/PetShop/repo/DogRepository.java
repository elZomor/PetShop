package com.assessment.PetShop.repo;

import com.assessment.PetShop.domain.Dog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DogRepository extends CrudRepository<Dog,Integer> {
    List<Dog> findAll() ;
    List<Dog> findByBreed(String breed) ;
    Optional<Dog> findByBreedAndSex(String breed , Character sex) ;
}
