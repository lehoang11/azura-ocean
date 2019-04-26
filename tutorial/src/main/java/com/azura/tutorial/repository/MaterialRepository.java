package com.azura.tutorial.repository;

import com.azura.tutorial.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, Long> {
}
