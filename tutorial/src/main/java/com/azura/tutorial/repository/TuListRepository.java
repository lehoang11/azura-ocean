package com.azura.tutorial.repository;

import com.azura.tutorial.model.TuList;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TuListRepository extends JpaRepository<TuList, Long >{
    TuList findById(Long id);

    boolean existsByCode(String code);
}
