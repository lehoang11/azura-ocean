package com.azura.tutorial.repository;

import com.azura.tutorial.model.TutorialLike;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TutorialLikeRepository extends JpaRepository<TutorialLike, Long> {
    long countByTutorialId(Long tutorialId);

    TutorialLike findByTutorialIdAndUserId(Long tutorialId, Long userId );

    TutorialLike findById(Long id);
}
