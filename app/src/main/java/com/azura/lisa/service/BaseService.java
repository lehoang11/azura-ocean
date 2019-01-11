package com.azura.lisa.service;

import com.topica.adapter.common.exception.BusinessException;
import org.modelmapper.ModelMapper;

public interface BaseService<M, I> {
    M save(M model) throws BusinessException;

    M get(I id) throws BusinessException;

    M update(M model) throws BusinessException;

    void delete(I id) throws BusinessException;

    ModelMapper maper = new ModelMapper();
}
