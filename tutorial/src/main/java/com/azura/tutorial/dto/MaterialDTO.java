package com.azura.tutorial.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MaterialDTO {

    private  Long    materialId;
    private  String  content;
    private  String  tutorialId;

    public MaterialDTO(Long materialId, String content, String tutorialId) {
        this.materialId = materialId;
        this.content = content;
        this.tutorialId = tutorialId;
    }
}
