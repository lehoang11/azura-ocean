package com.azura.lisa.service.impl;

import com.azura.common.exception.BusinessException;
import com.azura.lisa.model.edu.Edu;
import com.azura.lisa.model.edu.EduFollow;
import com.azura.lisa.repository.EduFollowRepository;
import com.azura.lisa.repository.EduRepository;
import com.azura.lisa.service.EduFollowService;
import com.azura.lisa.service.EduService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EduFollowServiceImpl implements EduFollowService {

    @Autowired
    EduFollowRepository eduFollowRepository;

    @Autowired
    EduService eduService;

    @Autowired
    EduRepository eduRepository;

    @Override
    public EduFollow findByEduIdAndUserId(Long eduId, Long userId) {
        return eduFollowRepository.findByEduIdAndUserId(eduId, userId);
    }

    @Override
    public Long countByEduId(Long eduId){
        return eduFollowRepository.countByEduId(eduId);
    }

    @Override
    public EduFollow saveEduFollow(EduFollow dataForm) throws BusinessException {
        Edu eduModal = eduService.findEduById(dataForm.getEduId());
        eduModal.setFollowTotal(eduModal.getFollowTotal() + 1);
        eduRepository.save(eduModal);
        return eduFollowRepository.save(dataForm);
    }

    @Override
    public void deleteEduFollow(EduFollow dataForm) throws BusinessException {
        Edu eduModal = eduService.findEduById(dataForm.getEduId());
        eduModal.setFollowTotal(eduModal.getFollowTotal() - 1);
        eduRepository.save(eduModal);
        eduFollowRepository.delete(dataForm.getId());
    }
}
