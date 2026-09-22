package com.nexus.portal.dto.response;

public class DepartmentResponse {
    private Long id;
    private Long facultyId;
    private String code;
    private String name;

    public DepartmentResponse() {
    }

    public DepartmentResponse(Long id, Long facultyId, String code, String name) {
        this.id = id;
        this.facultyId = facultyId;
        this.code = code;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
