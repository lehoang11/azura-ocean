package com.azura.tutorial.service.impl;

import com.azura.common.exception.BusinessException;
import com.azura.common.exception.ExceptionCode;
import com.azura.tutorial.model.Material;
import com.azura.tutorial.repository.MaterialRepository;
import com.azura.tutorial.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    MaterialRepository materialRepository;

    public Material saveMaterial(Material dataForm){
        return materialRepository.save(dataForm);
    }

    public Material  updateMaterial(Material dataForm) throws BusinessException {
        Material materialModel = materialRepository.getOne(dataForm.getId());
        if (materialModel == null) throw new BusinessException(ExceptionCode.Edu.EDU_NOT_FOUND, "material not found !");
        materialModel.setContent(dataForm.getContent());
        return materialRepository.save(materialModel);
    }
}
