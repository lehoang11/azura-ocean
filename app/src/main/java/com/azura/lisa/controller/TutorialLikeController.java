package com.azura.lisa.controller;


import com.azura.common.common.ApiDataResponse;
import com.azura.common.exception.AuthenticationException;
import com.azura.common.exception.BusinessException;
import com.azura.common.exception.ExceptionCode;
import com.azura.common.utils.Messages;
import com.azura.tutorial.model.TutorialLike;
import com.azura.tutorial.service.TutorialLikeService;
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
@RequestMapping("/api/like")
@Api(value = "Tutorial Like", description = "Tutorial Information", produces = MediaType.APPLICATION_JSON_VALUE)
public class TutorialLikeController {
    @Autowired
    Messages messages;

    @Autowired
    TutorialLikeService tutorialLikeService;

    /* API Created Edu */
    @RequestMapping(value = "/tutorial", method = RequestMethod.POST)
    @ApiOperation(value = " API create Edu", response = ApiDataResponse.class)
    public  ApiDataResponse tutorialLike(Authentication authentication, @RequestBody TutorialLike dataForm) throws AuthenticationException, BusinessException {
        log.info("Begin tutorial like: ");
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new AuthenticationException(ExceptionCode.Authentication.AUTHENTICATION_TOKEN_INVALID,
                    "Token is invalid !");
        }
        return new ApiDataResponse(tutorialLikeService.saveTutorialLike(dataForm), HttpStatus.OK.value(),
                messages.get("response.successful"));
    }

    /* API Created Edu */
    @RequestMapping(value = "/total", method = RequestMethod.POST)
    @ApiOperation(value = " API create Edu", response = ApiDataResponse.class)
    public  ApiDataResponse tutorialLikeTotal( @RequestBody TutorialLike dataForm)  {
        return new ApiDataResponse(tutorialLikeService.countTutorialByTutorialCode(dataForm.getTutorialCode()), HttpStatus.OK.value(),
                messages.get("response.successful"));
    }

    /* API Created Edu */
    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    @ApiOperation(value = " API create Edu", response = ApiDataResponse.class)
    public  ApiDataResponse filter( @RequestBody TutorialLike dataForm) {
        log.info("Begin tutorial like: ");

        return new ApiDataResponse(tutorialLikeService.findByTutorialCodeAndUserId(dataForm.getTutorialCode(), dataForm.getUserId()), HttpStatus.OK.value(),
                messages.get("response.successful"));
    }
}
