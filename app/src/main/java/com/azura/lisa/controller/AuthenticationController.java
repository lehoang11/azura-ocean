package com.azura.lisa.controller;

import com.adonisle.auth.model.Auth;
import com.adonisle.auth.service.AuthService;
import com.azura.common.common.ApiDataResponse;
import com.azura.common.exception.AuthenticationException;
import com.azura.common.exception.BusinessException;
import com.azura.common.exception.ErrorInfo;
import com.azura.common.exception.ExceptionCode;
import com.azura.common.util.Messages;
import com.azura.lisa.Request.LoginRequest;
import com.azura.lisa.Request.TokenVerifyRequest;
import com.azura.lisa.dto.UserDTO;
import com.azura.lisa.model.User;
import com.azura.lisa.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@Slf4j
@RequestMapping("/api")
@Api(value = "Authentication", description = "Authentication Information", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {
    @Autowired
    Messages messages;

    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /* API HV login */
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ApiOperation(value = " API login", response = ApiDataResponse.class)
    public ApiDataResponse login(@RequestBody LoginRequest loginRequest) throws BusinessException {
        log.info("Begin login: ");
        log.info("username: " + loginRequest.getUsername());

        String userName = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        UserDTO userDTO = null;
        if (StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(password)) {
            if (userName.contains("%40"))
                userName = userName.replace("%40", "@");
            Auth user = this.authService.findByUsername(userName);

            if (user != null && this.passwordEncoder.matches(password, user.getPassword())) {
                userDTO = userService.findUserById(user.getId());
                log.info("End login: successful");
            } else {
                ErrorInfo errorInfo = new ErrorInfo(ExceptionCode.Authentication.AUTHENTICATION_USER_PASSWORD_INVALID, HttpStatus.UNAUTHORIZED.value(), "Username or password is invalid !");
                return new ApiDataResponse(errorInfo, HttpStatus.OK.value(),
                        "Username or password is invalid !");
            }
        } else {
            ErrorInfo errorInfo = new ErrorInfo(ExceptionCode.Authentication.AUTHENTICATION_USER_PASSWORD_INVALID, HttpStatus.UNAUTHORIZED.value(), "Username or password is invalid !");
            return new ApiDataResponse(errorInfo, HttpStatus.OK.value(),
                    "Username or password is invalid !");
        }

        userDTO.buildToken(loginRequest);
        return new ApiDataResponse(userDTO, HttpStatus.OK.value(),
                messages.get("response.successful"));
    }

    /* API HV signup */
    @RequestMapping(value = "/user/signup", method = RequestMethod.POST)
    @ApiOperation(value = " API login", response = ApiDataResponse.class)
    public ApiDataResponse signup(@RequestBody User userModel) throws BusinessException {
        log.info("Begin signup: ");
        log.info("email: " + userModel.getEmail());
        userService.saveUser(userModel);
        //userDTO.buildToken();
        return new ApiDataResponse(true, HttpStatus.OK.value(),
                messages.get("response.successful"));
    }

    /* API Thông tin cá nhân HV */
    @RequestMapping(value = "/user/verify/token", method = RequestMethod.POST)
    @ApiOperation(value = "API lấy thông tin HV", response = UserDTO.class)
    public ApiDataResponse uservVerifyToken(Authentication authentication)
            throws AuthenticationException, BusinessException {
        log.info("Begin get info user: ");
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new AuthenticationException(ExceptionCode.Authentication.AUTHENTICATION_TOKEN_INVALID,
                    "Token is invalid !");
        }

        User user = (User) authentication.getPrincipal();
        UserDTO userDTO = userService.findUserById(user.getId());

        return new ApiDataResponse(userDTO, HttpStatus.OK.value(),
                messages.get("response.successful"));
    }
}
