package com.nexus.portal.dto.response;

import com.nexus.portal.enums.TopicStatus;
import com.nexus.portal.enums.TopicType;

import java.time.LocalDateTime;
import java.util.List;

public class TopicResponse {
    private Long id;
    private String title;
    private String description;
    private String objectives;
    private String requirements;
    private DepartmentResponse department;
    private Long periodId;
    private String periodName;
    private TopicType type;
    private Integer maxStudents;
    private String duration;
    private TopicStatus status;
    private Boolean isRegistrationOpen;
    private List<TopicLecturerResponse> advisors;
    private List<MajorResponse> majors;
    private long registeredTeamsCount;
    private TeamResponse assignedTeam;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TopicResponse() {
    }

    public TopicResponse(Long id, String title, String description, String objectives, String requirements,
                         DepartmentResponse department, Long periodId, String periodName, TopicType type,
                         Integer maxStudents, String duration, TopicStatus status, Boolean isRegistrationOpen,
                         List<TopicLecturerResponse> advisors, List<MajorResponse> majors,
                         long registeredTeamsCount, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(id, title, description, objectives, requirements, department, periodId, periodName, type,
                maxStudents, duration, status, isRegistrationOpen, advisors, majors, registeredTeamsCount,
                null, createdAt, updatedAt);
    }

    public TopicResponse(Long id, String title, String description, String objectives, String requirements,
                         DepartmentResponse department, Long periodId, String periodName, TopicType type,
                         Integer maxStudents, String duration, TopicStatus status, Boolean isRegistrationOpen,
                         List<TopicLecturerResponse> advisors, List<MajorResponse> majors,
                         long registeredTeamsCount, TeamResponse assignedTeam,
                         LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.objectives = objectives;
        this.requirements = requirements;
        this.department = department;
        this.periodId = periodId;
        this.periodName = periodName;
        this.type = type;
        this.maxStudents = maxStudents;
        this.duration = duration;
        this.status = status;
        this.isRegistrationOpen = isRegistrationOpen;
        this.advisors = advisors;
        this.majors = majors;
        this.registeredTeamsCount = registeredTeamsCount;
        this.assignedTeam = assignedTeam;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String title;
        private String description;
        private String objectives;
        private String requirements;
        private DepartmentResponse department;
        private Long periodId;
        private String periodName;
        private TopicType type;
        private Integer maxStudents;
        private String duration;
        private TopicStatus status;
        private Boolean isRegistrationOpen;
        private List<TopicLecturerResponse> advisors;
        private List<MajorResponse> majors;
        private long registeredTeamsCount;
        private TeamResponse assignedTeam;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder objectives(String objectives) {
            this.objectives = objectives;
            return this;
        }

        public Builder requirements(String requirements) {
            this.requirements = requirements;
            return this;
        }

        public Builder department(DepartmentResponse department) {
            this.department = department;
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

        public Builder type(TopicType type) {
            this.type = type;
            return this;
        }

        public Builder maxStudents(Integer maxStudents) {
            this.maxStudents = maxStudents;
            return this;
        }

        public Builder duration(String duration) {
            this.duration = duration;
            return this;
        }

        public Builder status(TopicStatus status) {
            this.status = status;
            return this;
        }

        public Builder isRegistrationOpen(Boolean isRegistrationOpen) {
            this.isRegistrationOpen = isRegistrationOpen;
            return this;
        }

        public Builder advisors(List<TopicLecturerResponse> advisors) {
            this.advisors = advisors;
            return this;
        }

        public Builder majors(List<MajorResponse> majors) {
            this.majors = majors;
            return this;
        }

        public Builder registeredTeamsCount(long registeredTeamsCount) {
            this.registeredTeamsCount = registeredTeamsCount;
            return this;
        }

        public Builder assignedTeam(TeamResponse assignedTeam) {
            this.assignedTeam = assignedTeam;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public TopicResponse build() {
            return new TopicResponse(id, title, description, objectives, requirements, department,
                    periodId, periodName, type, maxStudents, duration, status, isRegistrationOpen,
                    advisors, majors, registeredTeamsCount, assignedTeam, createdAt, updatedAt);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getObjectives() {
        return objectives;
    }

    public void setObjectives(String objectives) {
        this.objectives = objectives;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public DepartmentResponse getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentResponse department) {
        this.department = department;
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

    public TopicType getType() {
        return type;
    }

    public void setType(TopicType type) {
        this.type = type;
    }

    public Integer getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(Integer maxStudents) {
        this.maxStudents = maxStudents;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public TopicStatus getStatus() {
        return status;
    }

    public void setStatus(TopicStatus status) {
        this.status = status;
    }

    public Boolean getIsRegistrationOpen() {
        return isRegistrationOpen;
    }

    public void setIsRegistrationOpen(Boolean isRegistrationOpen) {
        this.isRegistrationOpen = isRegistrationOpen;
    }

    public List<TopicLecturerResponse> getAdvisors() {
        return advisors;
    }

    public void setAdvisors(List<TopicLecturerResponse> advisors) {
        this.advisors = advisors;
    }

    public List<MajorResponse> getMajors() {
        return majors;
    }

    public void setMajors(List<MajorResponse> majors) {
        this.majors = majors;
    }

    public long getRegisteredTeamsCount() {
        return registeredTeamsCount;
    }

    public void setRegisteredTeamsCount(long registeredTeamsCount) {
        this.registeredTeamsCount = registeredTeamsCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public TeamResponse getAssignedTeam() {
        return assignedTeam;
    }

    public void setAssignedTeam(TeamResponse assignedTeam) {
        this.assignedTeam = assignedTeam;
    }
}
