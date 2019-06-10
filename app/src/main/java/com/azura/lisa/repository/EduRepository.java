package com.azura.lisa.repository;

import com.azura.lisa.dto.edu.EduDTO;
import com.azura.lisa.model.edu.Edu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;


public interface EduRepository  extends JpaRepository<Edu, Long> {

    Edu findEduByShortNameAndId(String shortName, Long id);

    Edu findEduById(Long id);

    Edu findEduByUserId(Long userId);

    Edu findEduByShortName(String shortName);


    @Query(value = "SELECT NEW com.azura.lisa.dto.edu.EduDTO( edu.id, edu.name, edu.shortName, edu.userId, edu.avatar, edu.banner, edu.keyword, edu.status, edu.description ,edu.createdAt, edu.updatedAt, edu.followTotal) " +
            "FROM Edu edu " +
            "WHERE edu.status = 0 " +
            "AND ( edu.name like CONCAT('%', :q ,'%') OR  edu.keyword like CONCAT('%', :q ,'%')) " +
            "ORDER BY edu.id DESC ")
    @RestResource(exported = false)
    Page<EduDTO> searchEdu(@Param("q") String q, Pageable pageRequest );

    @Query(value = "SELECT NEW com.azura.lisa.dto.edu.EduDTO( edu.id, edu.name, edu.shortName, edu.userId, edu.avatar, edu.banner, edu.keyword, edu.status, edu.description ,edu.createdAt, edu.updatedAt, edu.followTotal) " +
            "FROM Edu edu " +
            "JOIN EduFollow fo ON edu.id = fo.eduId " +
            "WHERE edu.status = 0 " +
            "AND fo.userId =:userId " +
            "ORDER BY edu.id DESC ")
    @RestResource(exported = false)
    Page<EduDTO> filterEduFollow(@Param("userId") Long userId, Pageable pageRequest );
}
