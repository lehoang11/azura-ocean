package com.azura.tutorial.model;

import com.azura.common.model.CreatedTimestampModel;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tutorial_comment")
public class TutorialComment extends CreatedTimestampModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tutorial_code")
    private Long tutorialCode;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "content")
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTutorialCode() {
        return tutorialCode;
    }

    public void setTutorialCode(Long tutorialCode) {
        this.tutorialCode = tutorialCode;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
