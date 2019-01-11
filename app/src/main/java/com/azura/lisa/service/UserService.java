package com.azura.lisa.service;


import com.azura.common.exception.BusinessException;
import com.azura.lisa.dto.UserDTO;

public interface UserService {

    UserDTO findUserById(Long userId) throws BusinessException;

}
