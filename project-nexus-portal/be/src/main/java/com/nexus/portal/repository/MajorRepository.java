package com.nexus.portal.repository;

import com.nexus.portal.model.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MajorRepository extends JpaRepository<Major, Long> {
    List<Major> findByDepartmentId(Long departmentId);
    Optional<Major> findByCode(String code);
}
