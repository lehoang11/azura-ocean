package com.azura.tutorial.repository;

import com.azura.tutorial.dto.TutorialDTO;
import com.azura.tutorial.model.Tutorial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Date;
import java.util.List;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> , PagingAndSortingRepository<Tutorial, Long>, JpaSpecificationExecutor<Tutorial> {

    @Query(value = "SELECT NEW com.azura.tutorial.dto.TutorialDTO ( tu.id, tu.name,tu.shortName,tu.userId,tu.avatar,tu.medialUrl," +
            "tu.keyword,tu.generalCode, tu.viewTotal,tu.likeTotal,tu.status,tu.createdAt,e.id as eduId,e.name as eduName,e.shortName as eduShortName, e.avatar as eduAvatar) " +
            "FROM Tutorial tu " +
            "JOIN Edu e ON e.id = tu.eduId " +
            "WHERE tu.status = 0 " +
            "ORDER BY tu.id DESC ")
    @RestResource(exported = false)
    Page<TutorialDTO> filterTutorial( Pageable pageRequest);

    @Query(value = "SELECT NEW com.azura.tutorial.dto.TutorialDTO ( tu.id, tu.name,tu.shortName,tu.userId,tu.avatar,tu.medialUrl," +
            "tu.keyword,tu.generalCode, tu.viewTotal,tu.likeTotal,tu.status,tu.createdAt,m.content,e.id as eduId,e.name as eduName,e.shortName as eduShortName, e.avatar as eduAvatar) " +
            "FROM Tutorial tu " +
            "LEFT JOIN Material m ON tu.id = m.tutorialId " +
            "JOIN Edu e ON e.id = tu.eduId " +
            "WHERE tu.id =:id ")
    @RestResource(exported = false)
    TutorialDTO getTutorialById(@Param("id") Long id);

    @Query(value = "SELECT NEW com.azura.tutorial.dto.TutorialDTO ( tu.id, tu.name,tu.shortName,tu.userId,tu.avatar,tu.medialUrl," +
            "tu.keyword,tu.generalCode, tu.viewTotal,tu.likeTotal,tu.status,tu.createdAt,e.id as eduId,e.name as eduName,e.shortName as eduShortName, e.avatar as eduAvatar) " +
            "FROM Tutorial tu " +
            "JOIN Edu e ON e.id = tu.eduId " +
            "WHERE tu.eduId =:eduId " +
            "ORDER BY tu.id DESC ")
    @RestResource(exported = false)
    Page<TutorialDTO>  filterTutorialByEduId(@Param("eduId") Long eduId, Pageable pageRequest);

    @Query(value = "SELECT NEW com.azura.tutorial.dto.TutorialDTO ( tu.id, tu.name,tu.shortName,tu.userId,tu.avatar,tu.medialUrl," +
            "tu.keyword,tu.generalCode ,tu.viewTotal,tu.likeTotal,tu.status,tu.createdAt,e.id as eduId,e.name as eduName,e.shortName as eduShortName, e.avatar as eduAvatar) " +
            "FROM Tutorial tu " +
            "JOIN Edu e ON e.id = tu.eduId " +
            "WHERE e.shortName =:eduShortName " +
            "ORDER BY tu.id DESC ")
    @RestResource(exported = false)
    Page<TutorialDTO>  filterTutorialByEduShortName(@Param("eduShortName") String eduShortName, Pageable pageRequest);

    @Query(value = "SELECT NEW com.azura.tutorial.dto.TutorialDTO ( tu.id, tu.name,tu.shortName,tu.userId,tu.avatar,tu.medialUrl," +
            "tu.keyword,tu.generalCode, tu.viewTotal, tu.likeTotal,tu.status,tu.createdAt,e.id as eduId,e.name as eduName,e.shortName as eduShortName, e.avatar as eduAvatar) " +
            "FROM Tutorial tu " +
            "JOIN Edu e ON e.id = tu.eduId " +
            "WHERE tu.status = 0 " +
            "AND ( tu.name like CONCAT('%', :q ,'%') OR tu.keyword like CONCAT('%', :q ,'%'))" +
            "ORDER BY tu.id DESC ")
    @RestResource(exported = false)
    Page<TutorialDTO> searchTutorial(@Param("q") String q, Pageable pageRequest);

    Tutorial findById(Long id);
}
