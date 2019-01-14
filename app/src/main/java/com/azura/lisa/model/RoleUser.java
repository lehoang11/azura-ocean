package com.azura.lisa.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "role_user")
public class RoleUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_id", nullable = false)
    private Long roleId;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}
