package com.azura.lisa.service.impl;

import com.azura.common.exception.BusinessException;
import com.azura.common.exception.ExceptionCode;
import com.azura.lisa.model.edu.Edu;
import com.azura.lisa.repository.EduRepository;
import com.azura.lisa.service.EduService;
import com.azura.lisa.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EduServiceImpl implements EduService {

    @Autowired
    EduRepository eduRepository;

    public Edu  saveEdu(Edu eduModel){
        eduModel.setShortName(CommonUtils.covertStringToURL(eduModel.getName()));
        return eduRepository.save(eduModel);
    }

    @Override
    public Edu  updateEdu(Edu eduForm) throws BusinessException{
        Edu eduModel = eduRepository.getOne(eduForm.getId());
        if (eduModel == null) throw new BusinessException(ExceptionCode.Edu.EDU_NOT_FOUND, "edu not found !");
        eduModel.setName(eduForm.getName());
        eduModel.setShortName(CommonUtils.covertStringToURL(eduForm.getName()));
        eduModel.setKeyword(eduForm.getKeyword());
        return eduRepository.save(eduModel);
    }

    @Override
    public Edu findEduById(Long id) throws BusinessException {
        Edu eduModel = eduRepository.findEduById(id);
        if (eduModel == null) {
            throw new BusinessException(ExceptionCode.Edu.EDU_NOT_FOUND, "edu not found !");
        }
        return eduModel;
    }

    @Override
    public Edu findEduByShortNameAndId(String shortName,Long id) throws BusinessException {
        Edu eduModel = eduRepository.findEduByShortNameAndId(shortName, id);
        if (eduModel == null) throw new BusinessException(ExceptionCode.Edu.EDU_NOT_FOUND, "edu not found !");
        return eduModel;
    }

    @Override
    public Edu findEduByUserId(Long userId) throws BusinessException {
        return eduRepository.findEduByUserId(userId);
    }

    @Override
    public Edu findEduByShortName(String shortName) throws BusinessException{
        return eduRepository.findEduByShortName(shortName);
    }


    public boolean existsEduByShortName(String shortName){
        Edu eduObj = null;
        try {
            eduObj = this.findEduByShortName(shortName);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        if (eduObj != null) return true;
        return false;
    }
}
