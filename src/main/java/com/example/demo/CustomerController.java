package com.example.demo;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@CrossOrigin(origins = "*")
@RestController
@ExposesResourceFor(Customer.class)
@RequestMapping(value = "/customer", produces = "application/json")
public class CustomerController {

    @Autowired
    private CustomerRepository repository;
    @Autowired
    private CustomerResourceAssemble assembler;
    @Autowired
    private LoginTokenRepository loginrepo;
    private final org.slf4j.Logger Logger = LoggerFactory.getLogger(this.getClass());

    private boolean IsTokenValid(String Token)
    {
        Logger.debug("hit is token valid");
        Logger.debug("Token is " + Token);
        System.out.println("Token is"+Token);
        System.out.println(Integer.toString((loginrepo.getCount())));
        if(loginrepo.getCount() == 0)
        {
            return  false;
        }
        else{

            Optional<LoginToken> token = loginrepo.findById((long)1);
            if(token.isPresent())
            {
                System.out.println(token.get().getToken());
                String token_present = token.get().getToken();
                if(token_present.equals(Token))
                {
                    return  true;

                }
                else {
                    return false;
                }
            }
            else {
                return  false;
            }
        }

    }

    @RequestMapping(method = RequestMethod.GET )
    public ResponseEntity<Collection<CustomerResource>> findAllOrders(@RequestHeader String Token) {
        if(IsTokenValid(Token)) {
            Logger.debug("token is valid");
            List<Customer> customers = repository.findAll();
            return new ResponseEntity<>(assembler.toResourceCollection(customers), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<CustomerResource> createOrder(@RequestBody Customer customer , @RequestHeader String Token) {
        if(IsTokenValid(Token)) {
            Customer createdCustomer = repository.create(customer);
            return new ResponseEntity<>(assembler.toResource(createdCustomer), HttpStatus.CREATED);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CustomerResource> findOrderById(@PathVariable Long id , @RequestHeader String Token) {
        if (IsTokenValid(Token)){
            Optional<Customer> order = repository.findById(id);
            if (order.isPresent()) {
                return new ResponseEntity<>(assembler.toResource(order.get()), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id , @RequestHeader String Token) {
        if (IsTokenValid(Token)){
            boolean wasDeleted = repository.delete(id);
            HttpStatus responseStatus = wasDeleted ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(responseStatus);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }







}
