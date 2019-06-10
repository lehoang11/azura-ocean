package com.azura.lisa.controller;

import com.azura.common.common.ApiDataResponse;
import com.azura.common.contants.Constants;
import com.azura.common.exception.AuthenticationException;
import com.azura.common.exception.BusinessException;
import com.azura.common.exception.ExceptionCode;
import com.azura.common.utils.Messages;
import com.azura.lisa.model.edu.EduFollow;
import com.azura.lisa.service.EduFollowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/eduFollow")
@Api(value = "Edu follow", description = "Edu Information", produces = MediaType.APPLICATION_JSON_VALUE)
public class EduFollowController {
    @Autowired
    Messages messages;

    @Autowired
    EduFollowService eduFollowService;

    /* API show Edu */
    @RequestMapping(value = "/show/check", method = RequestMethod.GET)
    @ApiOperation(value = " API show Edu", response = ApiDataResponse.class)
    public  ApiDataResponse eduFollowCheck(@RequestParam("eduId") Long eduId, @RequestParam("userId") Long userId) throws BusinessException {
        log.info("Begin get info user: ");
        log.info("eduId: " + String.valueOf(eduId));
        log.info("userId: " + String.valueOf(userId));
        return new ApiDataResponse(eduFollowService.findByEduIdAndUserId(eduId, userId), HttpStatus.OK.value(),
                messages.get("response.successful"));
    }

    /* API count eduFollow */
    @RequestMapping(value = "/show/count", method = RequestMethod.GET)
    @ApiOperation(value = " API count eduFollow", response = ApiDataResponse.class)
    public  ApiDataResponse eduFollowCount(@RequestParam("eduId") Long eduId) throws BusinessException {
        log.info("Begin get info count: ");
        log.info("eduId: " + String.valueOf(eduId));
        return new ApiDataResponse(eduFollowService.countByEduId(eduId), HttpStatus.OK.value(),
                messages.get("response.successful"));
    }

    /* API save eduFollow */
    @RequestMapping(value = "/follow", method = RequestMethod.POST)
    @ApiOperation(value = " API save eduFollow", response = ApiDataResponse.class)
    public  ApiDataResponse eduFollow(Authentication authentication, @RequestBody EduFollow dataForm)  throws AuthenticationException, BusinessException  {
        log.info("Begin get info user: ");
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new AuthenticationException(ExceptionCode.Authentication.AUTHENTICATION_TOKEN_INVALID,
                    "Token is invalid !");
        }
        return new ApiDataResponse(eduFollowService.saveEduFollow(dataForm), HttpStatus.OK.value(),
                messages.get("response.successful"));
    }

    /* API save eduFollow */
    @RequestMapping(value = "/unfollow", method = RequestMethod.POST)
    @ApiOperation(value = " API save eduFollow", response = ApiDataResponse.class)
    public  ApiDataResponse eduUnFollow(Authentication authentication, @RequestBody EduFollow dataForm)  throws AuthenticationException, BusinessException  {
        log.info("Begin get info user: ");
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new AuthenticationException(ExceptionCode.Authentication.AUTHENTICATION_TOKEN_INVALID,
                    "Token is invalid !");
        }
        eduFollowService.deleteEduFollow(dataForm);
        return new ApiDataResponse(true, HttpStatus.OK.value(),
                messages.get("response.successful"));
    }


}
