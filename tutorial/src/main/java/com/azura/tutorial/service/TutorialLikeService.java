package com.azura.tutorial.service;

import com.azura.common.exception.BusinessException;

import com.azura.tutorial.model.TutorialLike;

import java.util.List;

public interface TutorialLikeService {

    TutorialLike saveTutorialLike(TutorialLike dataForm) throws BusinessException;

    long countTutorialByTutorialCode(Long tutorialCode);

    TutorialLike findByTutorialCodeAndUserId(Long tutorialCode, Long userId );

}
