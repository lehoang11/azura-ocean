package com.adonisle.auth.service;

import com.adonisle.auth.model.Auth;

public interface AuthService {
    Auth findByUsername(String var1);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    void Register(Auth user);
}
