package com.nexus.portal.service.impl;

import com.nexus.portal.dto.response.CohortResponse;
import com.nexus.portal.dto.response.DepartmentResponse;
import com.nexus.portal.dto.response.FacultyResponse;
import com.nexus.portal.dto.response.MajorResponse;
import com.nexus.portal.repository.CohortRepository;
import com.nexus.portal.repository.DepartmentRepository;
import com.nexus.portal.repository.FacultyRepository;
import com.nexus.portal.repository.MajorRepository;
import com.nexus.portal.service.AcademicService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AcademicServiceImpl implements AcademicService {

    private final FacultyRepository facultyRepository;
    private final DepartmentRepository departmentRepository;
    private final MajorRepository majorRepository;
    private final CohortRepository cohortRepository;

    public AcademicServiceImpl(FacultyRepository facultyRepository,
                               DepartmentRepository departmentRepository,
                               MajorRepository majorRepository,
                               CohortRepository cohortRepository) {
        this.facultyRepository = facultyRepository;
        this.departmentRepository = departmentRepository;
        this.majorRepository = majorRepository;
        this.cohortRepository = cohortRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<FacultyResponse> getAllFaculties() {
        return facultyRepository.findAll().stream()
                .map(f -> new FacultyResponse(f.getId(), f.getCode(), f.getName()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepartmentResponse> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(d -> new DepartmentResponse(d.getId(), d.getFaculty().getId(), d.getCode(), d.getName()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepartmentResponse> getDepartmentsByFaculty(Long facultyId) {
        if (facultyId != null) {
            return departmentRepository.findByFacultyId(facultyId).stream()
                    .map(d -> new DepartmentResponse(d.getId(), d.getFaculty().getId(), d.getCode(), d.getName()))
                    .collect(Collectors.toList());
        }
        return getAllDepartments();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MajorResponse> getAllMajors() {
        return majorRepository.findAll().stream()
                .map(m -> new MajorResponse(m.getId(), m.getDepartment().getId(), m.getCode(), m.getName()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MajorResponse> getMajorsByDepartment(Long departmentId) {
        if (departmentId != null) {
            return majorRepository.findByDepartmentId(departmentId).stream()
                    .map(m -> new MajorResponse(m.getId(), m.getDepartment().getId(), m.getCode(), m.getName()))
                    .collect(Collectors.toList());
        }
        return getAllMajors();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CohortResponse> getAllCohorts() {
        return cohortRepository.findAll().stream()
                .map(c -> new CohortResponse(c.getId(), c.getCode(), c.getName(), c.getAdmissionYear(), c.getGraduationYear()))
                .collect(Collectors.toList());
    }
}
