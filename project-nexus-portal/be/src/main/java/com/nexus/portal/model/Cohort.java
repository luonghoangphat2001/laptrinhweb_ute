package com.nexus.portal.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cohorts")
public class Cohort extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", length = 30, unique = true, nullable = false)
    private String code;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "admission_year", nullable = false)
    private Integer admissionYear;

    @Column(name = "graduation_year", nullable = false)
    private Integer graduationYear;

    public Cohort() {
    }

    public Cohort(Long id, String code, String name, Integer admissionYear, Integer graduationYear) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.admissionYear = admissionYear;
        this.graduationYear = graduationYear;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String code;
        private String name;
        private Integer admissionYear;
        private Integer graduationYear;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder admissionYear(Integer admissionYear) {
            this.admissionYear = admissionYear;
            return this;
        }

        public Builder graduationYear(Integer graduationYear) {
            this.graduationYear = graduationYear;
            return this;
        }

        public Cohort build() {
            return new Cohort(id, code, name, admissionYear, graduationYear);
        }
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
