package com.azura.tutorial.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class TutorialCommentDTO {

    private  Long    id;
    private  Long  tutorialId;
    private  String  content;
    private  Long    userId;
    private Date    createdAt;
    private String   userName;
    private String   userAvatar;


    public TutorialCommentDTO(Long id, Long tutorialId, String content, Long userId, Date createdAt, String userName, String userAvatar) {
        this.id = id;
        this.tutorialId = tutorialId;
        this.content = content;
        this.userId = userId;
        this.createdAt = createdAt;
        this.userName = userName;
        this.userAvatar = userAvatar;
    }
}
