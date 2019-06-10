package com.azura.tutorial.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TutorialLikeDTO {

    private String  tutorialLikeId;
    private String  tutorialId;
    private Long    userId;

    public TutorialLikeDTO(String tutorialLikeId, String tutorialId, Long userId) {
        this.tutorialLikeId = tutorialLikeId;
        this.tutorialId = tutorialId;
        this.userId = userId;
    }
}
