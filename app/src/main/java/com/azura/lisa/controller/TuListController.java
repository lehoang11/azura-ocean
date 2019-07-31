package com.azura.lisa.controller;


import com.azura.common.common.ApiDataResponse;
import com.azura.common.exception.AuthenticationException;
import com.azura.common.exception.ExceptionCode;
import com.azura.common.utils.Messages;
import com.azura.lisa.model.edu.Edu;
import com.azura.tutorial.model.TuList;
import com.azura.tutorial.service.TuListService;
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
@RequestMapping("/api/tulist")
@Api(value = "Tutorial List", description = "Tu Information", produces = MediaType.APPLICATION_JSON_VALUE)
public class TuListController {

    @Autowired
    Messages messages;

    @Autowired
    TuListService tuListService;

    /* API Created Tu */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = " API create Tu List", response = ApiDataResponse.class)
    public  ApiDataResponse tuListCreate(Authentication authentication, @RequestBody TuList tuListForm) throws AuthenticationException {
        log.info("Begin create tu list: ");
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new AuthenticationException(ExceptionCode.Authentication.AUTHENTICATION_TOKEN_INVALID,
                    "Token is invalid !");
        }

        return new ApiDataResponse(tuListService.saveTuList(tuListForm), HttpStatus.OK.value(),
                messages.get("response.successful"));
    }
}
