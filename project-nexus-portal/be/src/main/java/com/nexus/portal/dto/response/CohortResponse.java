package com.nexus.portal.dto.response;

public class CohortResponse {
    private Long id;
    private String code;
    private String name;
    private Integer admissionYear;
    private Integer graduationYear;

    public CohortResponse() {
    }

    public CohortResponse(Long id, String code, String name, Integer admissionYear, Integer graduationYear) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.admissionYear = admissionYear;
        this.graduationYear = graduationYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getAdmissionYear() {
        return admissionYear;
    }

    public void setAdmissionYear(Integer admissionYear) {
        this.admissionYear = admissionYear;
    }

    public Integer getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(Integer graduationYear) {
        this.graduationYear = graduationYear;
    }
}
