package com.azura.lisa.service.impl;

import com.azura.common.exception.BusinessException;
import com.azura.common.exception.ExceptionCode;
import com.azura.lisa.dto.UserDTO;
import com.azura.lisa.model.User;
import com.azura.lisa.repository.UserRepository;
import com.azura.lisa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO findUserById(Long userId) throws BusinessException{
        UserDTO userDTO = userRepository.findUserById(userId);
        if (userDTO == null) {
            throw new BusinessException(ExceptionCode.User.USER_NOT_FOUND, "user not found !");
        }
        return userDTO;
    }

    @Override
    public void saveUser(User user) throws BusinessException {
        user.setUsername(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
