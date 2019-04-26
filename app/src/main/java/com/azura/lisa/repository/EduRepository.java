package com.azura.lisa.repository;

import com.azura.lisa.model.edu.Edu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EduRepository  extends JpaRepository<Edu, Long> {

    Edu findEduByShortNameAndId(String shortName, Long id);

    Edu findEduById(Long id);

    Edu findEduByUserId(Long userId);

    Edu findEduByShortName(String shortName);
}
