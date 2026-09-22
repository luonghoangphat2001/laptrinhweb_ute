package com.nexus.portal.service;

import com.nexus.portal.dto.response.CohortResponse;
import com.nexus.portal.dto.response.DepartmentResponse;
import com.nexus.portal.dto.response.FacultyResponse;
import com.nexus.portal.dto.response.MajorResponse;

import java.util.List;

public interface AcademicService {
    List<FacultyResponse> getAllFaculties();
    List<DepartmentResponse> getAllDepartments();
    List<DepartmentResponse> getDepartmentsByFaculty(Long facultyId);
    List<MajorResponse> getAllMajors();
    List<MajorResponse> getMajorsByDepartment(Long departmentId);
    List<CohortResponse> getAllCohorts();
}
