package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@ExposesResourceFor(LoginToken.class)
@RequestMapping(value = "/Login", produces = "application/json")
public class LoginTokenController {

    @Autowired
    private LoginTokenRepository repository;
    @Autowired
    private LoginTokenResourceAssembler assembler;
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<LoginTokenResource> returnToken()
    {
        if (repository.getCount() == 0)
        {
            LoginToken token = new LoginToken();
            token.setToken(token.getSaltString());
            LoginToken tokenGenerated =  repository.create(token);
            return new ResponseEntity<>(assembler.toResource(tokenGenerated), HttpStatus.CREATED);
        }
        else
        {
            Long id = (long)1;
            LoginToken updatedToken = new LoginToken();
            updatedToken.setToken(updatedToken.getSaltString());
            boolean wasUpdated = repository.update(id, updatedToken);
            if (wasUpdated) {
                return new ResponseEntity<>(assembler.toResource(updatedToken), HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        }
    }
}
