package com.azura.lisa.service.impl;

import com.azura.common.exception.BusinessException;
import com.azura.common.exception.ExceptionCode;
import com.azura.lisa.Request.UserRequest;
import com.azura.lisa.dto.UserDTO;
import com.azura.lisa.model.User;
import com.azura.lisa.model.edu.Edu;
import com.azura.lisa.repository.UserRepository;
import com.azura.lisa.service.EduService;
import com.azura.lisa.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    EduService eduService;

    @Override
    public UserDTO filterUserById(Long userId) throws BusinessException{
        UserDTO userDTO = userRepository.filterUserById(userId);
        if (userDTO == null) {
            throw new BusinessException(ExceptionCode.User.USER_NOT_FOUND, "user not found !");
        }
        Edu eduDTO = eduService.findEduByUserId(userId);
        userDTO.setEdu(eduDTO);
        return userDTO;
    }

    @Override
    public void saveUser(User user) throws BusinessException {
        user.setUsername(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User updateUser(UserRequest userRequest) throws BusinessException {
        String key = userRequest.getKey();
        User userForm = userRequest.getUser();
        if(StringUtils.isEmpty(key)){
            throw new BusinessException(ExceptionCode.Edu.EDU_NOT_FOUND, "user update with key not found !");
        }
        User user = userRepository.findById(userForm.getId());
        switch (key) {
            case "username" :
                if (!StringUtils.isEmpty(userForm.getUsername()) && !userForm.getUsername().equals(user.getUsername())){
                    user.setUsername(userForm.getUsername());
                }
                break;
            case "email" :
                if (!StringUtils.isEmpty(userForm.getEmail()) && !userForm.getEmail().equals(user.getEmail())){
                    user.setEmail(userForm.getEmail());
                }
                break;
            case "name" :
                if (!StringUtils.isEmpty(userForm.getFirstName()) && !userForm.getFirstName().equals(user.getFirstName())){
                    user.setFirstName(userForm.getFirstName());
                }
                if (!StringUtils.isEmpty(userForm.getLastName()) && !userForm.getLastName().equals(user.getLastName())){
                    user.setLastName(userForm.getLastName());
                }
                break;
            case "avatar" :
                if (!StringUtils.isEmpty(userForm.getAvatar())){
                    user.setAvatar(userForm.getAvatar());
                }
                break;
            case "password" :

                if (!this.passwordEncoder.matches(userRequest.getPasswordOld(), user.getPassword())){
                    throw new BusinessException(ExceptionCode.Edu.EDU_NOT_FOUND, "password old not found!");
                }
                if (StringUtils.isEmpty(userForm.getPassword())){
                    throw new BusinessException(ExceptionCode.Edu.EDU_NOT_FOUND, "password required!");
                }
                user.setPassword(passwordEncoder.encode(userForm.getPassword()));
                break;

            default :
                throw new BusinessException(ExceptionCode.Edu.EDU_NOT_FOUND, "user update with key not found !");
        }

        return userRepository.save(user);
    }
}
