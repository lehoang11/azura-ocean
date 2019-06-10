package com.azura.lisa.repository;

import com.azura.lisa.model.edu.EduFollow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EduFollowRepository extends JpaRepository<EduFollow, Long> {

    EduFollow findByEduIdAndUserId(Long eduId, Long userId);

    Long countByEduId(Long eduId);
}
