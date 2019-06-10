package com.azura.tutorial.service;

import com.azura.tutorial.dto.TutorialCommentDTO;
import com.azura.tutorial.model.TutorialComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TutorialCommentService {

    Page<TutorialCommentDTO> filter(Long tutorialId, Pageable pageRequest);

    TutorialCommentDTO saveTutorialComment(TutorialComment dataForm);

    void deleteTutorialComment(Long id);
}
