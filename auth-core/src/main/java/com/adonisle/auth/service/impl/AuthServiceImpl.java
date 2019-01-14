package com.adonisle.auth.service.impl;

import com.adonisle.auth.model.Auth;
import com.adonisle.auth.repository.AuthRepository;
import com.adonisle.auth.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);
    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public AuthServiceImpl() {
    }



    public Auth findByEmail(String email) {
        log.info("(findByEmail)email:" + email);
        return this.authRepository.findByEmail(email);
    }

    public  Boolean existsByEmail(String email){ return authRepository.existsByEmail(email); }

    public void Register(Auth user){
        user.setEmail(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        authRepository.save(user);
    }
}
