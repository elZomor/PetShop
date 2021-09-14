package com.assessment.PetShop.repo;

import com.assessment.PetShop.domain.Dog;
import org.springframework.data.repository.CrudRepository;

public interface DogRepository extends CrudRepository<Dog,Integer> {
}
