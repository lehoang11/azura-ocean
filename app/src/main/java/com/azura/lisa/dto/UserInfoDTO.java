package com.azura.lisa.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserInfoDTO {
    @ApiModelProperty(value = "USER ID")
    private Long userID;
    
    @ApiModelProperty(value = "First name")
    private String firstName;
    
    @ApiModelProperty(value = "Last name")
    private String lastName;
    
    @ApiModelProperty(value = "Email")
    private String email;
    
    @ApiModelProperty(value = "Phone")
    private String phone1;
    
    @ApiModelProperty(value = "userlv select from data of mdl_user_info_data")
    private String userlv;

    
}
