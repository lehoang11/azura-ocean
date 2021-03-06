package com.azura.tutorial.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class TutorialDTO {

    private  Long    id;
    private  String  name;
    private  String  shortName;
    private  Long    userId;
    private  String  avatar;
    private  String  video;
    private  String  keyword;
    private  String  generalCode;
    private  Long    viewTotal;
    private  Long    likeTotal;
    private  int     status;
    private  Date    createdAt;
    private  String  content;
    private  Long    eduId;
    private  String  eduName;
    private  String  eduShortName;
    private  String  eduAvatar;
    private String vcrType;
    private String medialUrl;


    public TutorialDTO(Long id, String name, String shortName, Long userId, String avatar, String medialUrl, String keyword, String generalCode, Long viewTotal, Long likeTotal, int status, Date createdAt, String content, Long eduId, String eduName, String eduShortName, String eduAvatar) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.userId = userId;
        this.avatar = avatar;
        this.medialUrl = medialUrl;
        this.keyword = keyword;
        this.generalCode = generalCode;
        this.viewTotal = viewTotal;
        this.likeTotal = likeTotal;
        this.status = status;
        this.createdAt = createdAt;
        this.content = content;
        this.eduId = eduId;
        this.eduName = eduName;
        this.eduShortName = eduShortName;
        this.eduAvatar = eduAvatar;
    }

    public TutorialDTO(Long id, String name, String shortName, Long userId, String avatar, String medialUrl, String keyword, String generalCode, Long viewTotal, Long likeTotal, int status, Date createdAt, Long eduId, String eduName, String eduShortName, String eduAvatar) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.userId = userId;
        this.avatar = avatar;
        this.medialUrl = medialUrl;
        this.keyword = keyword;
        this.generalCode = generalCode;
        this.viewTotal = viewTotal;
        this.likeTotal = likeTotal;
        this.status = status;
        this.createdAt = createdAt;
        this.eduId = eduId;
        this.eduName = eduName;
        this.eduShortName = eduShortName;
        this.eduAvatar = eduAvatar;
    }


    // da du
    public TutorialDTO(Long id, String name, String shortName, Long userId, String avatar, String medialUrl, String keyword, String generalCode, Long viewTotal, Long likeTotal, int status, Date createdAt, String content, Long eduId, String eduName, String eduShortName, String eduAvatar, String vcrType ) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.userId = userId;
        this.avatar = avatar;
        this.medialUrl = medialUrl;
        this.keyword = keyword;
        this.generalCode = generalCode;
        this.viewTotal = viewTotal;
        this.likeTotal = likeTotal;
        this.status = status;
        this.createdAt = createdAt;
        this.content = content;
        this.eduId = eduId;
        this.eduName = eduName;
        this.eduShortName = eduShortName;
        this.eduAvatar = eduAvatar;
        this.vcrType = vcrType;

    }


}
