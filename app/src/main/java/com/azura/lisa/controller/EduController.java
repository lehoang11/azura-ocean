package com.azura.lisa.controller;

import com.azura.common.common.ApiDataResponse;
import com.azura.common.contants.Constants;
import com.azura.common.exception.AuthenticationException;
import com.azura.common.exception.BusinessException;
import com.azura.common.exception.ExceptionCode;
import com.azura.common.utils.Messages;
import com.azura.lisa.Request.EduRequest;
import com.azura.lisa.model.edu.Edu;
import com.azura.lisa.service.EduService;
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
@RequestMapping("/api/edu")
@Api(value = "Edu", description = "Edu Information", produces = MediaType.APPLICATION_JSON_VALUE)
public class EduController {

    @Autowired
    Messages messages;

    @Autowired
    EduService  eduService;

    /* API Created Edu */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ApiOperation(value = " API create Edu", response = ApiDataResponse.class)
    public  ApiDataResponse eduCreate(Authentication authentication, @RequestBody Edu eduModel) throws AuthenticationException {
        log.info("Begin get info user: ");
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new AuthenticationException(ExceptionCode.Authentication.AUTHENTICATION_TOKEN_INVALID,
                    "Token is invalid !");
        }

        return new ApiDataResponse(eduService.saveEdu(eduModel), HttpStatus.OK.value(),
                messages.get("response.successful"));
    }

    /* API update Edu */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = " API update Edu", response = ApiDataResponse.class)
    public  ApiDataResponse eduUpdate(Authentication authentication, @RequestBody EduRequest eduRequest) throws AuthenticationException, BusinessException {
        log.info("Begin get info user: ");
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new AuthenticationException(ExceptionCode.Authentication.AUTHENTICATION_TOKEN_INVALID,
                    "Token is invalid !");
        }
        return new ApiDataResponse(eduService.updateEdu(eduRequest), HttpStatus.OK.value(),
                messages.get("response.successful"));
    }

    /* API show Edu */
    @RequestMapping(value = "/show/{shortName}-{id}", method = RequestMethod.GET)
    @ApiOperation(value = " API show Edu", response = ApiDataResponse.class)
    public  ApiDataResponse eduShow(@PathVariable("shortName") String shortName, @PathVariable("id") long id) throws  BusinessException {
        log.info("Begin get info edu: ");
        return new ApiDataResponse(eduService.findEduByShortNameAndId(shortName, id), HttpStatus.OK.value(),
                messages.get("response.successful"));
    }

    /* API show Edu */
    @RequestMapping(value = "/show/{shortName}", method = RequestMethod.GET)
    @ApiOperation(value = " API show Edu", response = ApiDataResponse.class)
    public  ApiDataResponse eduShow(@PathVariable("shortName") String shortName ) throws  BusinessException {
        log.info("Begin get info edu: ");
        return new ApiDataResponse(eduService.findEduByShortName(shortName), HttpStatus.OK.value(),
                messages.get("response.successful"));
    }

    /* API show Edu */
    @RequestMapping(value = "/show/userId/{userId}", method = RequestMethod.GET)
    @ApiOperation(value = " API show Edu", response = ApiDataResponse.class)
    public  ApiDataResponse getEduByUserId(@PathVariable("userId") long userId ) throws  BusinessException {
        log.info("Begin get info edu: ");
        return new ApiDataResponse(eduService.findEduByUserId(userId), HttpStatus.OK.value(),
                messages.get("response.successful"));
    }

    /* API show Edu */
    @RequestMapping(value = "/show/id/{id}", method = RequestMethod.GET)
    @ApiOperation(value = " API show Edu", response = ApiDataResponse.class)
    public  ApiDataResponse getEduById(@PathVariable("id") long id ) throws  BusinessException {
        log.info("Begin get info edu: ");
        return new ApiDataResponse(eduService.findEduById(id), HttpStatus.OK.value(),
                messages.get("response.successful"));
    }

    /* API show Edu */
    @RequestMapping(value = "/show/exists/{shortName}", method = RequestMethod.GET)
    @ApiOperation(value = " API show Edu", response = ApiDataResponse.class)
    public  ApiDataResponse checkExistsByshortName(@PathVariable("shortName") String shortName ) throws  BusinessException {
        log.info("Begin get info edu: ");
        return new ApiDataResponse(eduService.existsEduByShortName(shortName), HttpStatus.OK.value(),
                messages.get("response.successful"));
    }

    /* API  eduFollow */
    @RequestMapping(value = "/show/getListFollow", method = RequestMethod.GET)
    @ApiOperation(value = " API  eduFollow", response = ApiDataResponse.class)
    public  ApiDataResponse eduFollow(@RequestParam("userId") Long userId, @RequestParam(value="currentPage", defaultValue = "20 ") Integer currentPage) throws BusinessException {
        log.info("Begin get edu Follow info: ");
        log.info("userId: " + String.valueOf(userId));
        Pageable pageRequest = new PageRequest(0, Constants.PAGE_SIZE);
        return new ApiDataResponse(eduService.filterEduFollow(userId, pageRequest), HttpStatus.OK.value(),
                messages.get("response.successful"));
    }





}
