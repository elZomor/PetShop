package com.assessment.PetShop.repo;

import com.assessment.PetShop.domain.OrderItem;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderItemRepository extends PagingAndSortingRepository<OrderItem,Integer> {
}
