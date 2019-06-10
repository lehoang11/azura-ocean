package com.azura.lisa.service;

import com.azura.common.exception.BusinessException;
import com.azura.lisa.Request.EduRequest;
import com.azura.lisa.dto.edu.EduDTO;
import com.azura.lisa.model.edu.Edu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface EduService {

    Edu saveEdu(Edu eduModel);

    Edu updateEdu(EduRequest eduRequest) throws BusinessException;

    Edu findEduById(Long id) throws BusinessException;

    Edu findEduByShortNameAndId(String shortName,Long id) throws BusinessException;

    Edu findEduByUserId(Long userId) throws BusinessException;

    Edu findEduByShortName(String  shortName) throws BusinessException;

    boolean existsEduByShortName(String shortName);

    Page<EduDTO> searchEdu(String q);

    Page<EduDTO> filterEduFollow(Long userId, Pageable pageRequest);
}
