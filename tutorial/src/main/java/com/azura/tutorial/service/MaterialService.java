package com.azura.tutorial.service;

import com.azura.common.exception.BusinessException;
import com.azura.tutorial.model.Material;

public interface MaterialService {

    Material saveMaterial(Material dataForm) throws BusinessException;

    Material updateMaterial(Material dataForm) throws BusinessException;
}
