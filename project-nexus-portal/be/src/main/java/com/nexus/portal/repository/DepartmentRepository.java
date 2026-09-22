package com.nexus.portal.repository;

import com.nexus.portal.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findByFacultyId(Long facultyId);
    Optional<Department> findByCode(String code);
}
