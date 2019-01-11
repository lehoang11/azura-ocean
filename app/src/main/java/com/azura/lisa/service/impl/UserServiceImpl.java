package com.azura.lisa.service.impl;

import com.azura.common.exception.BusinessException;
import com.azura.common.exception.ExceptionCode;
import com.azura.lisa.dto.UserDTO;
import com.azura.lisa.repository.UserRepository;
import com.azura.lisa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDTO findUserById(Long userId) throws BusinessException{
        UserDTO userDTO = userRepository.findUserById(userId);
        if (userDTO == null) {
            throw new BusinessException(ExceptionCode.User.USER_NOT_FOUND, "user not found !");
        }
        return userDTO;
    }
}
