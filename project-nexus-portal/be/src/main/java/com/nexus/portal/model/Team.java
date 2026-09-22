package com.nexus.portal.model;

import com.nexus.portal.enums.TeamStatus;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teams")
public class Team extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leader_id", nullable = false)
    private User leader;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "period_id", nullable = false)
    private RegistrationPeriod period;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty faculty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cohort_id", nullable = false)
    private Cohort cohort;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_id", nullable = false)
    private Major major;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 30, nullable = false)
    private TeamStatus status = TeamStatus.FORMING;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TeamMember> members = new HashSet<>();

    public Team() {
    }

    public Team(Long id, String name, User leader, RegistrationPeriod period, Faculty faculty,
                Cohort cohort, Major major, TeamStatus status, Set<TeamMember> members) {
        this.id = id;
        this.name = name;
        this.leader = leader;
        this.period = period;
        this.faculty = faculty;
        this.cohort = cohort;
        this.major = major;
        this.status = status != null ? status : TeamStatus.FORMING;
        this.members = members != null ? members : new HashSet<>();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String name;
        private User leader;
        private RegistrationPeriod period;
        private Faculty faculty;
        private Cohort cohort;
        private Major major;
        private TeamStatus status = TeamStatus.FORMING;
        private Set<TeamMember> members = new HashSet<>();

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder leader(User leader) {
            this.leader = leader;
            return this;
        }

        public Builder period(RegistrationPeriod period) {
            this.period = period;
            return this;
        }

        public Builder faculty(Faculty faculty) {
            this.faculty = faculty;
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

        public Builder status(TeamStatus status) {
            this.status = status;
            return this;
        }

        public Builder members(Set<TeamMember> members) {
            this.members = members;
            return this;
        }

        public Team build() {
            return new Team(id, name, leader, period, faculty, cohort, major, status, members);
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

    public User getLeader() {
        return leader;
    }

    public void setLeader(User leader) {
        this.leader = leader;
    }

    public RegistrationPeriod getPeriod() {
        return period;
    }

    public void setPeriod(RegistrationPeriod period) {
        this.period = period;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
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

    public TeamStatus getStatus() {
        return status;
    }

    public void setStatus(TeamStatus status) {
        this.status = status;
    }

    public Set<TeamMember> getMembers() {
        return members;
    }

    public void setMembers(Set<TeamMember> members) {
        this.members = members;
    }
}
