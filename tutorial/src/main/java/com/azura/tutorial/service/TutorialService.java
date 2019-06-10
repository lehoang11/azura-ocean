package com.azura.tutorial.service;

import com.azura.common.exception.BusinessException;
import com.azura.tutorial.Request.TutorialRequest;
import com.azura.tutorial.dto.TutorialDTO;
import com.azura.tutorial.model.Tutorial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface TutorialService {


    TutorialRequest saveTutorial(TutorialRequest dataForm) throws BusinessException;

    Tutorial updateTutorial(Tutorial dataForm) throws BusinessException;

    Page<TutorialDTO> filterTutorial();

    TutorialDTO getTutorialById(Long id);

    Page<TutorialDTO>  filterTutorialByEduId(Long eduId);

    Page<TutorialDTO>  filterTutorialByEduShortName(String eduShortName);

    Page<TutorialDTO> searchTutorial(String q, Pageable pageRequest);

    Tutorial findById(Long id);

    void updateView(Long id);
}
