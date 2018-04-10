package com.example.demo;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

public class CustomerResource extends ResourceSupport {
    private final long id;
    private final String firstName;
    private final String  lastName;

    public CustomerResource(Customer customer)
    {
        id = customer.getId();
        firstName = customer.getFirstName();
        lastName = customer.getLastName();
    }
    @JsonProperty("id")
    public Long getResourceId(){
        return  id;
    }
    public String getFirstName(){
        return  firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
