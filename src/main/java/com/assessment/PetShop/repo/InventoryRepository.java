package com.assessment.PetShop.repo;

import com.assessment.PetShop.domain.Dog;
import com.assessment.PetShop.domain.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory,Integer> {
    Optional<Inventory> findByDog(Dog d) ;
    List<Inventory> findAll() ;
}
