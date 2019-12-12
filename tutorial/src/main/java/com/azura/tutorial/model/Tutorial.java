package com.azura.tutorial.model;


import com.azura.common.model.TimestampModel;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tutorial")
public class Tutorial extends TimestampModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "edu_id")
    private Long eduId;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "video")
    private String video;

    @Column(name = "keyword")
    private String keyword;

    @Column(name = "general_code")
    private String generalCode;

    @Column(name = "view_total")
    private Long viewTotal;

    @Column(name = "like_total")
    private Long likeTotal;

    @Column(name = "status")
    private int status;

    @Column(name = "vcr_type")
    private String vcrType;

    @Column(name = "media_url")
    private String medialUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEduId() {
        return eduId;
    }

    public void setEduId(Long eduId) {
        this.eduId = eduId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getGeneralCode() {
        return generalCode;
    }

    public void setGeneralCode(String generalCode) {
        this.generalCode = generalCode;
    }

    public Long getViewTotal() {
        return viewTotal;
    }

    public void setViewTotal(Long viewTotal) {
        this.viewTotal = viewTotal;
    }

    public Long getLikeTotal() {
        return likeTotal;
    }

    public void setLikeTotal(Long likeTotal) {
        this.likeTotal = likeTotal;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getVcrType() {
        return vcrType;
    }

    public void setVcrType(String vcrType) {
        this.vcrType = vcrType;
    }

    public String getMedialUrl() {
        return medialUrl;
    }

    public void setMedialUrl(String medialUrl) {
        this.medialUrl = medialUrl;
    }


}
