package com.azura.lisa.Request;

import lombok.Data;

@Data
public class TokenVerifyRequest {

    private String username;
    private String token;

}