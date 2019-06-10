package com.azura.lisa.model.edu;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "edu_follow")
public class EduFollow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "edu_id")
    private Long eduId;

    @Column(name = "user_id")
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEduId() {
        return eduId;
    }

    public void setEduId(Long eduId) {
        this.eduId = eduId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
