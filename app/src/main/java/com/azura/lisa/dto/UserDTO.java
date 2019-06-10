package com.azura.lisa.dto;


import com.azura.lisa.Request.LoginRequest;
import com.azura.lisa.model.User;
import com.azura.lisa.config.TokenAuthenticationHelper;
import com.azura.lisa.model.edu.Edu;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String avatar;
    private int status;
    private Date createdAt;
    private String token;
    private Edu    edu;

    public UserDTO(Long id, String username, String email, String firstName, String lastName, String avatar, int status, Date createdAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
        this.status = status;
        this.createdAt = createdAt;
    }

    public void buildToken(LoginRequest request) {
        User user = new User();
        user.setId(this.id);
        user.setUsername(this.username);
        user.setPassword(request.getPassword());
        this.token = TokenAuthenticationHelper.generateToken(user);
    }

}
