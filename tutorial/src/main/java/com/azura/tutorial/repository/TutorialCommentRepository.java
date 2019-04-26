package com.azura.tutorial.repository;

import com.azura.tutorial.model.TutorialLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorialCommentRepository extends JpaRepository<TutorialLike, Long> {
}
