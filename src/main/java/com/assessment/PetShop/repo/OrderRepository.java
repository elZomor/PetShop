package com.assessment.PetShop.repo;

import com.assessment.PetShop.domain.Order;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderRepository extends PagingAndSortingRepository<Order,Integer> {
}
