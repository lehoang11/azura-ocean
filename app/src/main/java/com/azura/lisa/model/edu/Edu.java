package com.azura.lisa.model.edu;

import com.azura.common.model.TimestampModel;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "edu")
public class Edu extends TimestampModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "banner")
    private String banner;

    @Column(name = "keyword")
    private String keyword;

    @Column(name = "status")
    private int status;

    @Column(name = "description")
    private String description;

    @Column(name = "follow_total")
    private Long followTotal;

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getFollowTotal() {
        return followTotal;
    }

    public void setFollowTotal(Long followTotal) {
        this.followTotal = followTotal;
    }
}
