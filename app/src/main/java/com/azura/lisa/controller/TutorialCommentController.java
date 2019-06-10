package com.azura.lisa.controller;


import com.azura.common.common.ApiDataResponse;
import com.azura.common.exception.AuthenticationException;
import com.azura.common.exception.BusinessException;
import com.azura.common.exception.ExceptionCode;
import com.azura.common.utils.Messages;
import com.azura.tutorial.model.TutorialComment;
import com.azura.tutorial.service.TutorialCommentService;
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
@RequestMapping("/api/tutorialComment")
@Api(value = "Edu", description = "Comment Information", produces = MediaType.APPLICATION_JSON_VALUE)
public class TutorialCommentController {

    @Autowired
    Messages messages;

    @Autowired
    TutorialCommentService tutorialCommentService;

    /* API show Edu */
    @RequestMapping(value = "/show/filter", method = RequestMethod.GET)
    @ApiOperation(value = " API show comment", response = ApiDataResponse.class)
    public  ApiDataResponse filter(@RequestParam("tutorialId") Long tutorialId, @RequestParam("currentPage") Integer currentPage, @RequestParam("pageSize") Integer pageSize) throws BusinessException {
        log.info("Begin get info comment: ");
        log.info("tutorialCode: " + String.valueOf(tutorialId));
        log.info("currentPage: " + String.valueOf(currentPage));
        log.info("pageSize: " + String.valueOf(pageSize));
        if (currentPage == null){
            currentPage = 1;
        }
        if (pageSize == null) {
            pageSize = 20;
        }
        Pageable pageRequest = new PageRequest(currentPage -1 , pageSize);
        return new ApiDataResponse(tutorialCommentService.filter(tutorialId, pageRequest), HttpStatus.OK.value(),
                messages.get("response.successful"));
    }

    /* API Created create */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ApiOperation(value = " API create comment", response = ApiDataResponse.class)
    public  ApiDataResponse commentCreate(Authentication authentication, @RequestBody TutorialComment dataForm) throws AuthenticationException {
        log.info("Begin get info edu: ");
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new AuthenticationException(ExceptionCode.Authentication.AUTHENTICATION_TOKEN_INVALID,
                    "Token is invalid !");
        }

        return new ApiDataResponse(tutorialCommentService.saveTutorialComment(dataForm), HttpStatus.OK.value(),
                messages.get("response.successful"));
    }


    /* API save eduFollow */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ApiOperation(value = " API deltete comment", response = ApiDataResponse.class)
    public  ApiDataResponse delete(Authentication authentication, @PathVariable("id") long id)  throws AuthenticationException, BusinessException  {
        log.info("Begin get info delete comment: ");
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new AuthenticationException(ExceptionCode.Authentication.AUTHENTICATION_TOKEN_INVALID,
                    "Token is invalid !");
        }
         tutorialCommentService.deleteTutorialComment(id);
        return new ApiDataResponse(true, HttpStatus.OK.value(),
                messages.get("response.successful"));
    }


}
