package com.nexus.portal.dto.response;

public class MajorResponse {
    private Long id;
    private Long departmentId;
    private String code;
    private String name;

    public MajorResponse() {
    }

    public MajorResponse(Long id, Long departmentId, String code, String name) {
        this.id = id;
        this.departmentId = departmentId;
        this.code = code;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
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
