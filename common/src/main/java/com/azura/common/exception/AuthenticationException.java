package com.azura.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class AuthenticationException extends Exception {

    private static final long serialVersionUID = 1L;
    private String code;
    private String message;

}