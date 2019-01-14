package com.azura.lisa.Request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequest {

    @ApiModelProperty(value = "email")
    private String email;

    @ApiModelProperty(value = "password")
    private String password;

    @ApiModelProperty(value = "device")
    private String device;
    @ApiModelProperty(value = "appVersion")
    private String appVersion;
    @ApiModelProperty(value = "deviceId")
    private String deviceId;
    @ApiModelProperty(value = "operatingSystem")
    private String operatingSystem;
    @ApiModelProperty(value = "deviceToken")
    private String deviceToken;

}