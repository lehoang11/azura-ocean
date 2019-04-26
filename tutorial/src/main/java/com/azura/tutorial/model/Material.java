package com.azura.tutorial.model;


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

    @Column(name = "tutorial_code")
    private Long tutorialCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getTutorialCode() {
        return tutorialCode;
    }

    public void setTutorialCode(Long tutorialCode) {
        this.tutorialCode = tutorialCode;
    }
}
