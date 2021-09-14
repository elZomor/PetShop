package com.assessment.PetShop.repo;

import com.assessment.PetShop.domain.Dog;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DogRepository extends CrudRepository<Dog,Integer> {
    public List<Dog> findAll() ;
    public List<Dog> findByBreed(String breed) ;
}
