package com.azura.lisa.dto;

import com.azura.lisa.dto.edu.EduDTO;
import com.azura.tutorial.dto.TutorialDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@NoArgsConstructor
public class SearchDTO {

    private Page<TutorialDTO> tutorialDTO;
    private List<EduDTO> eduDTO;

    public SearchDTO(Page<TutorialDTO> tutorialDTO, List<EduDTO> eduDTO) {
        this.tutorialDTO = tutorialDTO;
        this.eduDTO = eduDTO;
    }
}
