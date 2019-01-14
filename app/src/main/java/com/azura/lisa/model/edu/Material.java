package com.azura.lisa.model.edu;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "material")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

}
