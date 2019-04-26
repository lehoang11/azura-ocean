package com.azura.lisa.dto;

import com.azura.lisa.model.edu.Edu;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonalInfoDTO {
    private UserDTO userDTO;
    private Edu edu;

    public PersonalInfoDTO(UserDTO userDTO, Edu edu) {
        this.userDTO = userDTO;
        this.edu = edu;
    }
}
