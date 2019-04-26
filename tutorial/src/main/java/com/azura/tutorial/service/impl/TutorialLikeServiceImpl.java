package com.azura.tutorial.service.impl;

import com.azura.tutorial.model.TutorialLike;
import com.azura.tutorial.repository.TutorialLikeRepository;
import com.azura.tutorial.service.TutorialLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorialLikeServiceImpl implements TutorialLikeService {
    @Autowired
    TutorialLikeRepository tutorialLikeRepository;

    @Override
    public TutorialLike saveTutorialLike(TutorialLike dataForm){
        if (dataForm.getId() != null) {
            TutorialLike TutorialLikeModel = tutorialLikeRepository.getOne(dataForm.getId());
            TutorialLikeModel.setStatus(99);
            return tutorialLikeRepository.save(TutorialLikeModel);
        }
        return tutorialLikeRepository.save(dataForm);
    }

    @Override
    public long countTutorialByTutorialCode(Long tutorialCode){
        int status = 0;
        return tutorialLikeRepository.countByTutorialCodeAndStatus(tutorialCode, status);
    }
    @Override
    public TutorialLike findByTutorialCodeAndUserId(Long tutorialCode, Long userId ){
        return tutorialLikeRepository.findByTutorialCodeAndUserId(tutorialCode, userId);
    }

}
