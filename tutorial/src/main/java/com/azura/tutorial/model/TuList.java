package com.azura.tutorial.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tu_list")
public class TuList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String code;

    @Column(name = "edu_id")
    private Long eduId;
}
