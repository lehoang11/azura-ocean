package com.azura.tutorial.Request;

import com.azura.tutorial.model.Material;
import com.azura.tutorial.model.Tutorial;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TutorialRequest {

    private Tutorial tutorial;
    private Material material;


    public TutorialRequest(Tutorial tutorial, Material material) {
        this.tutorial = tutorial;
        this.material = material;
    }
}