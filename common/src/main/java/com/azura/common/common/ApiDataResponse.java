package com.azura.common.common;

import com.azura.common.utils.Messages;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ApiDataResponse<T> {


    private static @Autowired
    Messages messages;

    @ApiModelProperty(value = "data")
    private T data;

    @ApiModelProperty(value = "code")
    private int code;

    @ApiModelProperty(value = "message")
    private String message;

    public ApiDataResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ApiDataResponse ok(Object data) {
        return new ApiDataResponse(data, HttpStatus.OK.value(), "successful");
    }

    public static ApiDataResponse error(int statusCode, String message) {
        return new ApiDataResponse(null, statusCode, message);
    }

    public static ApiDataResponse error(Object data, int statusCode) {
        return new ApiDataResponse(data, statusCode, "");
    }

    public static ApiDataResponse error(Object data, int statusCode, String message) {
        return new ApiDataResponse(data, statusCode, message);
    }


}

