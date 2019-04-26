package com.azura.lisa.service;

import com.azura.common.exception.BusinessException;
import com.azura.lisa.model.edu.Edu;

public interface EduService {

    Edu saveEdu(Edu eduModel);

    Edu updateEdu(Edu eduForm) throws BusinessException;

    Edu findEduById(Long id) throws BusinessException;

    Edu findEduByShortNameAndId(String shortName,Long id) throws BusinessException;

    Edu findEduByUserId(Long userId) throws BusinessException;

    Edu findEduByShortName(String  shortName) throws BusinessException;

    boolean existsEduByShortName(String shortName);
}
