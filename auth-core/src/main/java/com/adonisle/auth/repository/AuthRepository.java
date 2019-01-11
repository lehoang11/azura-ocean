package com.adonisle.auth.repository;

import com.adonisle.auth.model.Auth;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AuthRepository extends PagingAndSortingRepository<Auth, Long> {

    Auth findByUsername(String var1);

    Auth findByEmail(String var1);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
