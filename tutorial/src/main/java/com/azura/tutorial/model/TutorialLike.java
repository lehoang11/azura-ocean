package com.azura.tutorial.model;


import com.azura.common.model.CreatedTimestampModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@AllArgsConstructor
@Data
@Entity
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Table(name = "tutorial_like")
public class TutorialLike extends CreatedTimestampModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tutorial_id")
    private Long tutorialId;

    @Column(name = "user_id")
    private Long userId;




}
