package com.nexus.portal.controller;

import com.nexus.portal.dto.response.*;
import com.nexus.portal.service.AcademicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/academic")
@Tag(name = "Academic Master Data", description = "Endpoints for academic faculties, departments, majors, and cohorts")
public class AcademicController {

    private final AcademicService academicService;

    public AcademicController(AcademicService academicService) {
        this.academicService = academicService;
    }

    @GetMapping("/faculties")
    @Operation(summary = "Get all faculties", description = "Retrieve list of all faculties")
    public ResponseEntity<ApiResponse<List<FacultyResponse>>> getAllFaculties() {
        List<FacultyResponse> faculties = academicService.getAllFaculties();
        return ResponseEntity.ok(ApiResponse.success(faculties, "Faculties retrieved successfully"));
    }

    @GetMapping("/departments")
    @Operation(summary = "Get departments by faculty", description = "Retrieve departments for a specific faculty ID (facultyId is required)")
    public ResponseEntity<ApiResponse<List<DepartmentResponse>>> getDepartments(
            @Parameter(description = "Faculty ID", required = true) @RequestParam Long facultyId) {
        List<DepartmentResponse> departments = academicService.getDepartmentsByFaculty(facultyId);
        return ResponseEntity.ok(ApiResponse.success(departments, "Departments retrieved successfully"));
    }

    @GetMapping("/departments/all")
    @Operation(summary = "Get all departments", description = "Retrieve all departments across all faculties")
    public ResponseEntity<ApiResponse<List<DepartmentResponse>>> getAllDepartments() {
        List<DepartmentResponse> departments = academicService.getAllDepartments();
        return ResponseEntity.ok(ApiResponse.success(departments, "All departments retrieved successfully"));
    }

    @GetMapping("/majors")
    @Operation(summary = "Get majors by department", description = "Retrieve majors for a specific department ID (departmentId is required)")
    public ResponseEntity<ApiResponse<List<MajorResponse>>> getMajors(
            @Parameter(description = "Department ID", required = true) @RequestParam Long departmentId) {
        List<MajorResponse> majors = academicService.getMajorsByDepartment(departmentId);
        return ResponseEntity.ok(ApiResponse.success(majors, "Majors retrieved successfully"));
    }

    @GetMapping("/majors/all")
    @Operation(summary = "Get all majors", description = "Retrieve all majors across all departments")
    public ResponseEntity<ApiResponse<List<MajorResponse>>> getAllMajors() {
        List<MajorResponse> majors = academicService.getAllMajors();
        return ResponseEntity.ok(ApiResponse.success(majors, "All majors retrieved successfully"));
    }

    @GetMapping("/cohorts")
    @Operation(summary = "Get all cohorts", description = "Retrieve list of all academic cohorts/intakes")
    public ResponseEntity<ApiResponse<List<CohortResponse>>> getAllCohorts() {
        List<CohortResponse> cohorts = academicService.getAllCohorts();
        return ResponseEntity.ok(ApiResponse.success(cohorts, "Cohorts retrieved successfully"));
    }
}
