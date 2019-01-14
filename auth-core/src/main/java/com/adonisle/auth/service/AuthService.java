package com.adonisle.auth.service;

import com.adonisle.auth.model.Auth;

public interface AuthService {

    Auth findByEmail(String var1);


    Boolean existsByEmail(String email);

    void Register(Auth user);
}
