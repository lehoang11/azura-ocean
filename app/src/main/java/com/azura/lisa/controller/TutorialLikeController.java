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
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/tutorialLike")
@Api(value = "Tutorial Like", description = "Tutorial Information", produces = MediaType.APPLICATION_JSON_VALUE)
public class TutorialLikeController {
    @Autowired
    Messages messages;

    @Autowired
    TutorialLikeService tutorialLikeService;

    /* API Created Edu */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ApiOperation(value = " API create like tutorial", response = ApiDataResponse.class)
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
        return new ApiDataResponse(tutorialLikeService.countTutorialByTutorialId(dataForm.getTutorialId()), HttpStatus.OK.value(),
                messages.get("response.successful"));
    }

    /* API Created Edu */
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    @ApiOperation(value = " API create Edu", response = ApiDataResponse.class)
    public  ApiDataResponse check(Authentication authentication ,@RequestParam("tutorialId") Long tutorialId,@RequestParam("userId") Long userId)throws AuthenticationException {
        log.info("Begin tutorial like: ");
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new AuthenticationException(ExceptionCode.Authentication.AUTHENTICATION_TOKEN_INVALID,
                    "Token is invalid !");
        }
        return new ApiDataResponse(tutorialLikeService.findByTutorialIdAndUserId(tutorialId, userId), HttpStatus.OK.value(),
                messages.get("response.successful"));
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ApiOperation(value = " API delete", response = ApiDataResponse.class)
    public  ApiDataResponse delete(Authentication authentication, @PathVariable("id") long id) throws AuthenticationException, BusinessException {
        log.info("Begin tutorial like delete: ");
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new AuthenticationException(ExceptionCode.Authentication.AUTHENTICATION_TOKEN_INVALID,
                    "Token is invalid !");
        }
        tutorialLikeService.deleteTutorialLike(id);
        return new ApiDataResponse(true, HttpStatus.OK.value(),
                messages.get("response.successful"));
    }
}
