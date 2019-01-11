package com.azura.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class BusinessException extends Exception {

    private static final long serialVersionUID = 1L;
    private String code;
    private String message;

}
