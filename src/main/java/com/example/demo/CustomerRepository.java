package com.example.demo;

import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository  extends  InMemoryRepository<Customer>{

    protected void updateIfExists(Customer original, Customer updated) {
        original.setFirstName(updated.getFirstName());
        original.setLastName(updated.getLastName());
    }

}
