package com.adonisle.auth.service.impl;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoderImpl implements PasswordEncoder {

    public PasswordEncoderImpl() {
    }

    public String encode(CharSequence rawPassword) {
        return BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt(12)).replace("$2a$", "$2j$");
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String javaEncodedPassword = encodedPassword.replace("$2j$", "$2a$");
        return BCrypt.checkpw(rawPassword.toString(), javaEncodedPassword);
    }
}
