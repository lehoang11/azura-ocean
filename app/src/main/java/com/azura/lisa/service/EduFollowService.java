package com.azura.lisa.service;

import com.azura.common.exception.BusinessException;
import com.azura.lisa.model.edu.EduFollow;

public interface EduFollowService {

    EduFollow findByEduIdAndUserId(Long eduId, Long userId);

    Long countByEduId(Long eduId);

    EduFollow saveEduFollow(EduFollow dataForm) throws BusinessException;

    void deleteEduFollow(EduFollow dataForm) throws BusinessException;
}
