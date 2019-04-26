package com.azura.lisa.controller;

import com.azura.common.common.ApiDataResponse;
import com.azura.common.exception.AuthenticationException;
import com.azura.common.exception.BusinessException;
import com.azura.common.exception.ExceptionCode;
import com.azura.common.utils.Messages;
import com.azura.lisa.model.edu.Edu;
import com.azura.tutorial.Request.TutorialRequest;
import com.azura.tutorial.model.Tutorial;
import com.azura.tutorial.service.TutorialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/tutorial")
@Api(value = "tutorial", description = "tutorial Information", produces = MediaType.APPLICATION_JSON_VALUE)
public class TutorialController {

    @Autowired
    Messages messages;

    @Autowired
    TutorialService tutorialService;

    /* API Created Edu */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ApiOperation(value = " API create Edu", response = ApiDataResponse.class)
    public  ApiDataResponse TutorialCreate(Authentication authentication, @RequestBody TutorialRequest dataForm) throws AuthenticationException,BusinessException {
        log.info("Begin create Tutorial: ");
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new AuthenticationException(ExceptionCode.Authentication.AUTHENTICATION_TOKEN_INVALID,
                    "Token is invalid !");
        }

        return new ApiDataResponse(tutorialService.saveTutorial(dataForm), HttpStatus.OK.value(),
                messages.get("response.successful"));
    }

    /* API update Tutorial */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ApiOperation(value = " API update Tutorial", response = ApiDataResponse.class)
    public  ApiDataResponse TutorialUpdate(Authentication authentication, @RequestBody Tutorial dataForm) throws AuthenticationException, BusinessException {
        log.info("Begin update Tutorial: ");
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new AuthenticationException(ExceptionCode.Authentication.AUTHENTICATION_TOKEN_INVALID,
                    "Token is invalid !");
        }
        return new ApiDataResponse(tutorialService.updateTutorial(dataForm), HttpStatus.OK.value(),
                messages.get("response.successful"));
    }

    /* API show Tutorial */
    @RequestMapping(value = "show/list", method = RequestMethod.GET)
    @ApiOperation(value = " API getListTutorial ", response = ApiDataResponse.class)
    public  ApiDataResponse getListTutorial()  {
        log.info("Begin get info tutorial: ");
        return new ApiDataResponse(tutorialService.filterTutorial(), HttpStatus.OK.value(),
                messages.get("response.successful"));
    }

    /* API show Tutorial */
    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    @ApiOperation(value = " API show Tutorial ", response = ApiDataResponse.class)
    public  ApiDataResponse showTutorial(@PathVariable("id") long id)  {
        log.info("Begin get info tutorial: ");
        log.info("Tutorial: " + String.valueOf(id));
        return new ApiDataResponse(tutorialService.getTutorialById(id), HttpStatus.OK.value(),
                messages.get("response.successful"));
    }
}
