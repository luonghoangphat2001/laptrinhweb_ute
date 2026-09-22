package com.nexus.portal.model;

import com.nexus.portal.enums.PeriodStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "registration_periods")
public class RegistrationPeriod extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 150, nullable = false)
    private String name;

    @Column(name = "academic_year", length = 20, nullable = false)
    private String academicYear;

    @Column(name = "semester", nullable = false)
    private Integer semester;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "submission_deadline", nullable = false)
    private LocalDateTime submissionDeadline;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 30, nullable = false)
    private PeriodStatus status = PeriodStatus.DRAFT;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "registration_period_faculties",
            joinColumns = @JoinColumn(name = "period_id"),
            inverseJoinColumns = @JoinColumn(name = "faculty_id")
    )
    private Set<Faculty> targetFaculties = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "registration_period_cohorts",
            joinColumns = @JoinColumn(name = "period_id"),
            inverseJoinColumns = @JoinColumn(name = "cohort_id")
    )
    private Set<Cohort> targetCohorts = new HashSet<>();

    public RegistrationPeriod() {
    }

    public RegistrationPeriod(Long id, String name, String academicYear, Integer semester,
                              LocalDateTime startDate, LocalDateTime endDate, LocalDateTime submissionDeadline,
                              PeriodStatus status, Set<Faculty> targetFaculties, Set<Cohort> targetCohorts) {
        this.id = id;
        this.name = name;
        this.academicYear = academicYear;
        this.semester = semester;
        this.startDate = startDate;
        this.endDate = endDate;
        this.submissionDeadline = submissionDeadline;
        this.status = status != null ? status : PeriodStatus.DRAFT;
        this.targetFaculties = targetFaculties != null ? targetFaculties : new HashSet<>();
        this.targetCohorts = targetCohorts != null ? targetCohorts : new HashSet<>();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String name;
        private String academicYear;
        private Integer semester;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private LocalDateTime submissionDeadline;
        private PeriodStatus status = PeriodStatus.DRAFT;
        private Set<Faculty> targetFaculties = new HashSet<>();
        private Set<Cohort> targetCohorts = new HashSet<>();

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder academicYear(String academicYear) {
            this.academicYear = academicYear;
            return this;
        }

        public Builder semester(Integer semester) {
            this.semester = semester;
            return this;
        }

        public Builder startDate(LocalDateTime startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(LocalDateTime endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder submissionDeadline(LocalDateTime submissionDeadline) {
            this.submissionDeadline = submissionDeadline;
            return this;
        }

        public Builder status(PeriodStatus status) {
            this.status = status;
            return this;
        }

        public Builder targetFaculties(Set<Faculty> targetFaculties) {
            this.targetFaculties = targetFaculties;
            return this;
        }

        public Builder targetCohorts(Set<Cohort> targetCohorts) {
            this.targetCohorts = targetCohorts;
            return this;
        }

        public RegistrationPeriod build() {
            return new RegistrationPeriod(id, name, academicYear, semester, startDate, endDate, submissionDeadline, status, targetFaculties, targetCohorts);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getSubmissionDeadline() {
        return submissionDeadline;
    }

    public void setSubmissionDeadline(LocalDateTime submissionDeadline) {
        this.submissionDeadline = submissionDeadline;
    }

    public PeriodStatus getStatus() {
        return status;
    }

    public void setStatus(PeriodStatus status) {
        this.status = status;
    }

    public Set<Faculty> getTargetFaculties() {
        return targetFaculties;
    }

    public void setTargetFaculties(Set<Faculty> targetFaculties) {
        this.targetFaculties = targetFaculties;
    }

    public Set<Cohort> getTargetCohorts() {
        return targetCohorts;
    }

    public void setTargetCohorts(Set<Cohort> targetCohorts) {
        this.targetCohorts = targetCohorts;
    }
}
