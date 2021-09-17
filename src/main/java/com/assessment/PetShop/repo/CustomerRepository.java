package com.assessment.PetShop.repo;

import com.assessment.PetShop.domain.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
public interface CustomerRepository extends CrudRepository<Customer,Integer> {
    Optional<Customer> findByEmail(String email);
}
