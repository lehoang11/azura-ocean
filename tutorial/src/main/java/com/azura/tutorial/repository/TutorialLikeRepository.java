package com.azura.tutorial.repository;

import com.azura.tutorial.model.TutorialLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TutorialLikeRepository extends JpaRepository<TutorialLike, Long> {
    long countByTutorialCodeAndStatus(Long tutorialCode,int status);

    TutorialLike findByTutorialCodeAndUserId(Long tutorialCode, Long userId );
}
