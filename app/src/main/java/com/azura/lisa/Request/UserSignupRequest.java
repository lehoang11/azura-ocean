package com.azura.lisa.Request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserSignupRequest {

    @ApiModelProperty(value = "username")
    private String username;

    @ApiModelProperty(value = "email")
    private String email;

    @ApiModelProperty(value = "password")
    private String password;


}
