package com.azura.lisa.model.edu;

import com.azura.common.model.TimestampModel;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "class_edu")
public class ClassEdu extends TimestampModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "school_id")
    private Long schoolId;

    @Column(name = "image")
    private String image;

    @Column(name = "subject_code")
    private String subjectCode;

    @Column(name = "video")
    private String video;

    @Column(name = "desciption")
    private String desciption;
}
