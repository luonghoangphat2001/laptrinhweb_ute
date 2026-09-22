package com.nexus.portal.dto.response;

import com.nexus.portal.enums.TeamStatus;

import java.time.LocalDateTime;
import java.util.List;

public class TeamResponse {
    private Long id;
    private String name;
    private Long leaderId;
    private String leaderName;
    private Long periodId;
    private String periodName;
    private FacultyResponse faculty;
    private CohortResponse cohort;
    private MajorResponse major;
    private TeamStatus status;
    private List<TeamMemberResponse> members;
    private LocalDateTime createdAt;

    public TeamResponse() {
    }

    public TeamResponse(Long id, String name, Long leaderId, String leaderName, Long periodId,
                        String periodName, FacultyResponse faculty, CohortResponse cohort,
                        MajorResponse major, TeamStatus status, List<TeamMemberResponse> members,
                        LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.leaderId = leaderId;
        this.leaderName = leaderName;
        this.periodId = periodId;
        this.periodName = periodName;
        this.faculty = faculty;
        this.cohort = cohort;
        this.major = major;
        this.status = status;
        this.members = members;
        this.createdAt = createdAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String name;
        private Long leaderId;
        private String leaderName;
        private Long periodId;
        private String periodName;
        private FacultyResponse faculty;
        private CohortResponse cohort;
        private MajorResponse major;
        private TeamStatus status;
        private List<TeamMemberResponse> members;
        private LocalDateTime createdAt;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder leaderId(Long leaderId) {
            this.leaderId = leaderId;
            return this;
        }

        public Builder leaderName(String leaderName) {
            this.leaderName = leaderName;
            return this;
        }

        public Builder periodId(Long periodId) {
            this.periodId = periodId;
            return this;
        }

        public Builder periodName(String periodName) {
            this.periodName = periodName;
            return this;
        }

        public Builder faculty(FacultyResponse faculty) {
            this.faculty = faculty;
            return this;
        }

        public Builder cohort(CohortResponse cohort) {
            this.cohort = cohort;
            return this;
        }

        public Builder major(MajorResponse major) {
            this.major = major;
            return this;
        }

        public Builder status(TeamStatus status) {
            this.status = status;
            return this;
        }

        public Builder members(List<TeamMemberResponse> members) {
            this.members = members;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public TeamResponse build() {
            return new TeamResponse(id, name, leaderId, leaderName, periodId, periodName, faculty, cohort, major, status, members, createdAt);
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

    public Long getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Long leaderId) {
        this.leaderId = leaderId;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public Long getPeriodId() {
        return periodId;
    }

    public void setPeriodId(Long periodId) {
        this.periodId = periodId;
    }

    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }

    public FacultyResponse getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyResponse faculty) {
        this.faculty = faculty;
    }

    public CohortResponse getCohort() {
        return cohort;
    }

    public void setCohort(CohortResponse cohort) {
        this.cohort = cohort;
    }

    public MajorResponse getMajor() {
        return major;
    }

    public void setMajor(MajorResponse major) {
        this.major = major;
    }

    public TeamStatus getStatus() {
        return status;
    }

    public void setStatus(TeamStatus status) {
        this.status = status;
    }

    public List<TeamMemberResponse> getMembers() {
        return members;
    }

    public void setMembers(List<TeamMemberResponse> members) {
        this.members = members;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
