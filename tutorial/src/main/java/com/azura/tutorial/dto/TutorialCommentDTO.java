package com.azura.tutorial.dto;

import lombok.Data;

@Data

public class TutorialCommentDTO {

    private  Long    tutorialCommentId;
    private  String  tutorialCode;
    private  Long    userId;
    private  String  content;
}
