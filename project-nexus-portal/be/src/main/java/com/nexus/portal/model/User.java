package com.nexus.portal.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 60, nullable = false)
    private String username;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "full_name", length = 120, nullable = false)
    private String fullName;

    @Column(name = "student_code", length = 30)
    private String studentCode;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "avatar_url", length = 255)
    private String avatarUrl;

    @Column(name = "is_active", nullable = false)
    private boolean active = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cohort_id")
    private Cohort cohort;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_id")
    private Major major;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_faculties",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "faculty_id")
    )
    private Set<Faculty> faculties = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_departments",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "department_id")
    )
    private Set<Department> departments = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_majors",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "major_id")
    )
    private Set<Major> advisoryMajors = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_cohorts",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "cohort_id")
    )
    private Set<Cohort> advisoryCohorts = new HashSet<>();

    public User() {
    }

    public User(Long id, String username, String email, String password, String fullName, String studentCode,
                String phone, String avatarUrl, boolean active, Cohort cohort, Major major,
                Set<Role> roles, Set<Faculty> faculties, Set<Department> departments,
                Set<Major> advisoryMajors, Set<Cohort> advisoryCohorts) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.studentCode = studentCode;
        this.phone = phone;
        this.avatarUrl = avatarUrl;
        this.active = active;
        this.cohort = cohort;
        this.major = major;
        this.roles = roles != null ? roles : new HashSet<>();
        this.faculties = faculties != null ? faculties : new HashSet<>();
        this.departments = departments != null ? departments : new HashSet<>();
        this.advisoryMajors = advisoryMajors != null ? advisoryMajors : new HashSet<>();
        this.advisoryCohorts = advisoryCohorts != null ? advisoryCohorts : new HashSet<>();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String username;
        private String email;
        private String password;
        private String fullName;
        private String studentCode;
        private String phone;
        private String avatarUrl;
        private boolean active = true;
        private Cohort cohort;
        private Major major;
        private Set<Role> roles = new HashSet<>();
        private Set<Faculty> faculties = new HashSet<>();
        private Set<Department> departments = new HashSet<>();
        private Set<Major> advisoryMajors = new HashSet<>();
        private Set<Cohort> advisoryCohorts = new HashSet<>();

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder studentCode(String studentCode) {
            this.studentCode = studentCode;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder avatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
            return this;
        }

        public Builder active(boolean active) {
            this.active = active;
            return this;
        }

        public Builder cohort(Cohort cohort) {
            this.cohort = cohort;
            return this;
        }

        public Builder major(Major major) {
            this.major = major;
            return this;
        }

        public Builder roles(Set<Role> roles) {
            this.roles = roles;
            return this;
        }

        public Builder faculties(Set<Faculty> faculties) {
            this.faculties = faculties;
            return this;
        }

        public Builder departments(Set<Department> departments) {
            this.departments = departments;
            return this;
        }

        public Builder advisoryMajors(Set<Major> advisoryMajors) {
            this.advisoryMajors = advisoryMajors;
            return this;
        }

        public Builder advisoryCohorts(Set<Cohort> advisoryCohorts) {
            this.advisoryCohorts = advisoryCohorts;
            return this;
        }

        public User build() {
            return new User(id, username, email, password, fullName, studentCode, phone, avatarUrl, active,
                    cohort, major, roles, faculties, departments, advisoryMajors, advisoryCohorts);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Cohort getCohort() {
        return cohort;
    }

    public void setCohort(Cohort cohort) {
        this.cohort = cohort;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(Set<Faculty> faculties) {
        this.faculties = faculties;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    public Set<Major> getAdvisoryMajors() {
        return advisoryMajors;
    }

    public void setAdvisoryMajors(Set<Major> advisoryMajors) {
        this.advisoryMajors = advisoryMajors;
    }

    public Set<Cohort> getAdvisoryCohorts() {
        return advisoryCohorts;
    }

    public void setAdvisoryCohorts(Set<Cohort> advisoryCohorts) {
        this.advisoryCohorts = advisoryCohorts;
    }
}
