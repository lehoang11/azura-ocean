package com.azura.tutorial.service.impl;

import com.azura.tutorial.model.Tutorial;
import com.azura.tutorial.model.TutorialLike;
import com.azura.tutorial.repository.TutorialLikeRepository;
import com.azura.tutorial.repository.TutorialRepository;
import com.azura.tutorial.service.TutorialLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TutorialLikeServiceImpl implements TutorialLikeService {
    @Autowired
    TutorialLikeRepository tutorialLikeRepository;

    @Autowired
    TutorialRepository tutorialRepository;

    @Override
    public TutorialLike saveTutorialLike(TutorialLike dataForm){

        TutorialLike res = tutorialLikeRepository.save(dataForm);
        if (res != null){
            Tutorial tutorialModel = tutorialRepository.findById(dataForm.getTutorialId());
            tutorialModel.setLikeTotal(tutorialModel.getLikeTotal() + 1);
            tutorialRepository.save(tutorialModel);
        }
        return res;
    }

    @Override
    public long countTutorialByTutorialId(Long tutorialId){
        return tutorialLikeRepository.countByTutorialId(tutorialId);
    }

    @Override
    public TutorialLike findByTutorialIdAndUserId(Long tutorialId, Long userId ){
        return tutorialLikeRepository.findByTutorialIdAndUserId(tutorialId, userId);
    }

    @Override
    public void deleteTutorialLike(Long id) {
        TutorialLike tutorialLike = tutorialLikeRepository.findById(id);
        Tutorial tutorialModel = tutorialRepository.findById(tutorialLike.getTutorialId());
        tutorialModel.setLikeTotal(tutorialModel.getLikeTotal() - 1);
        tutorialRepository.save(tutorialModel);
        tutorialLikeRepository.delete(id);
    }

}
