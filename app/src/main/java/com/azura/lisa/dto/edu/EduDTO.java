package com.azura.lisa.dto.edu;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EduDTO {
    private  Long  eduId;
    private  String name;
    private  String shortName;
    private  Long userId;
    private  String avatar;
    private  String banner;
    private  String keyword;
    private  int    status;
    private  String description;
    private Date createdAt;
    private Date updatedAt;
    private  Long followTotal;

    public EduDTO(Long eduId, String name, String shortName, Long userId, String avatar, String banner, String keyword, int status, String description, Date createdAt, Date updatedAt, Long followTotal) {
        this.eduId = eduId;
        this.name = name;
        this.shortName = shortName;
        this.userId = userId;
        this.avatar = avatar;
        this.banner = banner;
        this.keyword = keyword;
        this.status = status;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.followTotal = followTotal;
    }
}
