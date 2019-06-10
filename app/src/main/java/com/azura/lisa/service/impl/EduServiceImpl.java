package com.azura.lisa.service.impl;

import com.azura.common.exception.BusinessException;
import com.azura.common.exception.ExceptionCode;
import com.azura.lisa.Request.EduRequest;
import com.azura.lisa.dto.edu.EduDTO;
import com.azura.lisa.model.edu.Edu;
import com.azura.lisa.repository.EduRepository;
import com.azura.lisa.service.EduService;
import com.azura.lisa.utils.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class EduServiceImpl implements EduService {

    @Autowired
    EduRepository eduRepository;

    public Edu  saveEdu(Edu eduModel){
        eduModel.setShortName(CommonUtils.covertStringToURL(eduModel.getName()));
        eduModel.setFollowTotal(0l);
        return eduRepository.save(eduModel);
    }

    @Override
    public Edu  updateEdu(EduRequest eduRequest) throws BusinessException {
        if(StringUtils.isEmpty(eduRequest.getKey())){
            throw new BusinessException(ExceptionCode.Edu.EDU_NOT_FOUND, "edu update with key not found !");
        }
        String key = eduRequest.getKey();
        Edu eduForm = eduRequest.getEdu();

        Edu eduModel = eduRepository.getOne(eduForm.getId());
        if (eduModel == null) throw new BusinessException(ExceptionCode.Edu.EDU_NOT_FOUND, "edu not found !");

        switch(key) {
            case "name" :
                if (!StringUtils.isEmpty(eduForm.getName()) && !eduForm.getName().equals(eduModel.getName())){
                    eduModel.setName(eduForm.getName());
                }
                break;
            case "shortName":
                if (!StringUtils.isEmpty(eduForm.getShortName()) && !eduForm.getShortName().equals(eduModel.getShortName())){
                    eduModel.setShortName(eduForm.getShortName());
                }
                break;
            case "keyword":
                eduModel.setKeyword(eduForm.getKeyword());
                break;
            case "description":
                eduModel.setDescription(eduForm.getDescription());
                break;
            case "avatar":
                eduModel.setAvatar(eduForm.getAvatar());
                break;
            case "banner":
                eduModel.setBanner(eduForm.getBanner());
                break;

            default :
                // Statements
        }
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

    public Page<EduDTO> searchEdu(String q){
        Pageable pageRequest = new PageRequest(0,5);
        return eduRepository.searchEdu(q, pageRequest);
    }

    @Override
    public Page<EduDTO> filterEduFollow(Long userId, Pageable pageRequest) {
        return eduRepository.filterEduFollow(userId, pageRequest);
    }
}
