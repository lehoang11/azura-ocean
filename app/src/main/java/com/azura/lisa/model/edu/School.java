package com.azura.lisa.model.edu;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "school")
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column( name = "user_id", nullable = false)
    private String userId;
}
