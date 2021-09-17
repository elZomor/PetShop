package com.assessment.PetShop.repo;

import com.assessment.PetShop.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;
@RepositoryRestResource(collectionResourceRel = "customers", path = "customers")
public interface CustomerRepository extends CrudRepository<Customer,Integer> {
    Optional<Customer> findByEmail(String email);
}
