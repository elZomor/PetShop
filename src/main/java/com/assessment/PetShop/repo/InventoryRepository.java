package com.assessment.PetShop.repo;

import com.assessment.PetShop.domain.Dog;
import com.assessment.PetShop.domain.Inventory;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface InventoryRepository extends PagingAndSortingRepository<Inventory,Integer> {
    Optional<Inventory> findByDog(Dog d) ;
}
