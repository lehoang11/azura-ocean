package com.azura.lisa.dto;


import com.azura.lisa.Request.LoginRequest;
import com.azura.lisa.model.User;
import com.azura.lisa.config.TokenAuthenticationHelper;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String token;

    public UserDTO(Long id, String firstName, String lastName, String userName, String email) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
    }

    public void buildToken(LoginRequest request) {
        User user = new User();
        user.setId(this.id);
        user.setUsername(this.userName);
        user.setPassword(request.getPassword());
        this.token = TokenAuthenticationHelper.generateToken(user);
    }

}
