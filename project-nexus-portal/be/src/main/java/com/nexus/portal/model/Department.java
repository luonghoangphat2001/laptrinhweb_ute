package com.nexus.portal.model;

import jakarta.persistence.*;

@Entity
@Table(name = "departments")
public class Department extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty faculty;

    @Column(name = "code", length = 30, unique = true, nullable = false)
    private String code;

    @Column(name = "name", length = 150, nullable = false)
    private String name;

    public Department() {
    }

    public Department(Long id, Faculty faculty, String code, String name) {
        this.id = id;
        this.faculty = faculty;
        this.code = code;
        this.name = name;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private Faculty faculty;
        private String code;
        private String name;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder faculty(Faculty faculty) {
            this.faculty = faculty;
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

        public Department build() {
            return new Department(id, faculty, code, name);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
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
