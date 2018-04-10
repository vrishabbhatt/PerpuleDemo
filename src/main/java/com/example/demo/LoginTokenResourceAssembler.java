package com.example.demo;

import org.springframework.stereotype.Component;
@Component
public class LoginTokenResourceAssembler extends  ResourceAssembler<LoginToken , LoginTokenResource> {

    @Override
    public LoginTokenResource toResource(LoginToken token)
    {
        LoginTokenResource resource = new LoginTokenResource(token);
        return resource;
    }

}
