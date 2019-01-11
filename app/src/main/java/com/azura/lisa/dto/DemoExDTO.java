package com.azura.lisa.dto;

import lombok.Data;

@Data
public class DemoExDTO {
    private String classId;
    private String dateTime;
    private String timeRange;
    private String teacherName;
    private String subject;
    private String objective;
    private String typeClass;
    private String thumbnail;
    private String material;
    private String materialMp4;

    public DemoExDTO(String classId, String dateTime, String timeRange, String teacherName, String subject, String objective, String typeClass, String thumbnail, String material, String materialMp4) {
        this.classId = classId;
        this.dateTime = dateTime;
        this.timeRange = timeRange;
        this.teacherName = teacherName;
        this.subject = subject;
        this.objective = objective;
        this.typeClass = typeClass;
        this.thumbnail = thumbnail;
        this.material = material;
        this.materialMp4 = materialMp4;
    }

    public DemoExDTO() {

    }
}
