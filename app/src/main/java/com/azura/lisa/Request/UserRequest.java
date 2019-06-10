package com.azura.lisa.Request;

import com.azura.lisa.model.User;
import lombok.Data;

@Data
public class UserRequest {
    private String key;
    private User user;
    private  String passwordOld;
}
