package com.azura.lisa.service;


import com.azura.common.exception.BusinessException;
import com.azura.lisa.dto.UserDTO;
import com.azura.lisa.model.User;

public interface UserService {

    UserDTO findUserById(Long userId) throws BusinessException;

    void saveUser(User user) throws BusinessException;

}
