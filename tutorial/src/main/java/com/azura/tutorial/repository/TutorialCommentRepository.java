package com.azura.tutorial.repository;

import com.azura.tutorial.dto.TutorialCommentDTO;
import com.azura.tutorial.model.TutorialComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;


public interface TutorialCommentRepository extends JpaRepository<TutorialComment, Long> {

    @Query(value = "SELECT NEW com.azura.tutorial.dto.TutorialCommentDTO (tc.id, tc.tutorialId,tc.content, tc.userId,tc.createdAt, CONCAT(u.firstName, ' ', u.lastName) , u.avatar) " +
            "FROM TutorialComment tc " +
            "JOIN User u ON tc.userId = u.id " +
            "WHERE tc.tutorialId =:tutorialId " +
            "ORDER BY tc.id DESC ")
    @RestResource(exported = false)
    Page<TutorialCommentDTO> filter(@Param("tutorialId") Long tutorialId, Pageable pageRequest);

    @Query(value = "SELECT NEW com.azura.tutorial.dto.TutorialCommentDTO (tc.id, tc.tutorialId,tc.content, tc.userId,tc.createdAt, CONCAT(u.firstName, ' ', u.lastName) , u.avatar) " +
            "FROM TutorialComment tc " +
            "JOIN User u ON tc.userId = u.id " +
            "WHERE tc.id =:commentId " +
            "ORDER BY tc.id DESC ")
    @RestResource(exported = false)
    TutorialCommentDTO findByCommentId(@Param("commentId") Long commentId);


}
