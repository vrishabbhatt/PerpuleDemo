package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

public class LoginTokenResource extends ResourceSupport {

    private final Long id;
    private final String token;

    public LoginTokenResource(LoginToken token)
    {
        this.id = token.getId();
        this.token = token.getToken();
    }

    @JsonProperty("id")
    public Long getResourceId(){
        return  id;
    }
    public String getToken(){
        return  this.token;
    }

}
