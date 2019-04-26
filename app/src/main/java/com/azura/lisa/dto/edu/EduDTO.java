package com.azura.lisa.dto.edu;

import lombok.Data;

@Data
public class EduDTO {
    private  Long  eduId;
    private  String name;
    private  String shortName;
    private  String userId;
    private  String avatar;
    private  String banner;
    private  String keyword;
    private  int    status;
    private  String description;
}
