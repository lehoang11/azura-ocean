package com.azura.lisa.controller;


import com.azura.common.common.ApiDataResponse;
import com.azura.common.exception.AuthenticationException;
import com.azura.common.exception.BusinessException;

import com.azura.common.exception.ExceptionCode;
import com.azura.common.utils.Messages;

import com.azura.lisa.Request.UserRequest;
import com.azura.lisa.dto.UserDTO;
import com.azura.lisa.model.User;
import com.azura.lisa.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@RequestMapping("/api")
@Api(value = "User info", description = "User Information", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    Messages messages;


    @Autowired
    UserService userService;


    /* API HV login */
    @RequestMapping(value = "/user/getInfo", method = RequestMethod.POST)
    @ApiOperation(value = " API login", response = ApiDataResponse.class)
    public ApiDataResponse getUserInfo(Authentication authentication) throws AuthenticationException, BusinessException {
        log.info("Begin get info user: ");
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new AuthenticationException(ExceptionCode.Authentication.AUTHENTICATION_TOKEN_INVALID,
                    "Token is invalid !");
        }

        User user = (User) authentication.getPrincipal();
        UserDTO userDTO = userService.filterUserById(user.getId());


        return new ApiDataResponse(userDTO, HttpStatus.OK.value(),
                messages.get("response.successful"));
    }

    /* API HV login */
    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    @ApiOperation(value = " API update user", response = ApiDataResponse.class)
    public ApiDataResponse userUpdate(Authentication authentication, @RequestBody UserRequest userRequest) throws AuthenticationException, BusinessException {
        log.info("Begin get info user: ");
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new AuthenticationException(ExceptionCode.Authentication.AUTHENTICATION_TOKEN_INVALID,
                    "Token is invalid !");
        }

        User user = (User) authentication.getPrincipal();
        if(userRequest.getUser().getId() != user.getId()){
            throw new AuthenticationException(ExceptionCode.Authentication.AUTHENTICATION_TOKEN_INVALID,
                    "user not found!");
        }

        return new ApiDataResponse(userService.updateUser(userRequest), HttpStatus.OK.value(),
                messages.get("response.successful"));
    }





}
