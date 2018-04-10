package com.example.demo;

import org.springframework.stereotype.Repository;

@Repository
public class LoginTokenRepository extends InMemoryRepository<LoginToken> {

    protected void updateIfExists(LoginToken original, LoginToken updated) {
        original.setToken(updated.getToken());
    }
}
