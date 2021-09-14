package com.assessment.PetShop.repo;

import com.assessment.PetShop.domain.Inventory;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InventoryRepository extends PagingAndSortingRepository<Inventory,Integer> {
}
