package com.azura.tutorial.Request;

import com.azura.tutorial.model.Material;
import com.azura.tutorial.model.Tutorial;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TutorialRequest {

    private Tutorial tutorial;
    private Material material;

    public TutorialRequest() {
        super();
    }

    public TutorialRequest(Tutorial tutorial, Material material) {
        this.tutorial = tutorial;
        this.material = material;
    }
}