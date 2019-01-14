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

    @Column(nullable = false)
    private Long roleId;

    @Column(nullable = false)
    private Long userId;
}
