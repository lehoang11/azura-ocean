package com.azura.lisa.repository;

import com.azura.lisa.dto.UserDTO;
import com.azura.lisa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT NEW com.azura.lisa.dto.UserDTO ( u.id, u.username,u.email, u.firstName, u.lastName,u.avatar,u.status,u.createdAt)"
            + " FROM User u "
            + "WHERE u.id = :userId")
    @RestResource(exported = false)
    UserDTO filterUserById(@Param("userId") Long userId);

    User findById(Long id);
}
