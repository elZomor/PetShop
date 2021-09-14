package com.assessment.PetShop.repo;

import com.assessment.PetShop.domain.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer,Integer> {
}
