package com.nexus.portal.repository;

import com.nexus.portal.model.Cohort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CohortRepository extends JpaRepository<Cohort, Long> {
    Optional<Cohort> findByCode(String code);
}
