package com.azura.tutorial.service.impl;

import com.azura.tutorial.dto.TutorialCommentDTO;
import com.azura.tutorial.model.TutorialComment;
import com.azura.tutorial.repository.TutorialCommentRepository;
import com.azura.tutorial.service.TutorialCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TutorialCommentServiceImpl implements TutorialCommentService {

    @Autowired
    TutorialCommentRepository tutorialCommentRepository;

    @Override
    public Page<TutorialCommentDTO> filter(Long tutorialId, Pageable pageRequest) {
        return tutorialCommentRepository.filter(tutorialId, pageRequest);
    }

    @Override
    public TutorialCommentDTO saveTutorialComment(TutorialComment dataForm) {
        TutorialComment commentModel = tutorialCommentRepository.save(dataForm);
        if(commentModel == null) return null;

        return tutorialCommentRepository.findByCommentId(commentModel.getId());
    }

    @Override
    public void deleteTutorialComment(Long id) {
        tutorialCommentRepository.delete(id);
    }
}
