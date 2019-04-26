package com.azura.tutorial.service;

import com.azura.common.exception.BusinessException;
import com.azura.tutorial.Request.TutorialRequest;
import com.azura.tutorial.dto.TutorialDTO;
import com.azura.tutorial.model.Tutorial;
import org.springframework.data.domain.Page;


public interface TutorialService {


    TutorialRequest saveTutorial(TutorialRequest dataForm) throws BusinessException;

    Tutorial updateTutorial(Tutorial dataForm) throws BusinessException;

    Page<TutorialDTO> filterTutorial();

    TutorialDTO getTutorialById(Long id);

    Page<TutorialDTO>  filterTutorialByEduId(Long eduId);
}
