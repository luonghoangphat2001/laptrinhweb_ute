package com.nexus.portal.dto.response;

import com.nexus.portal.enums.PostStatus;
import com.nexus.portal.enums.PostType;

import java.time.LocalDateTime;
import java.util.Set;

public class MatchmakingPostResponse {
    private Long id;
    private PostType postType;
    private Long authorId;
    private String authorName;
    private String authorStudentCode;
    private String authorAvatarUrl;
    private Long teamId;
    private String teamName;
    private String title;
    private String goals;
    private DepartmentResponse department;
    private FacultyResponse faculty;
    private CohortResponse cohort;
    private Integer slotsNeeded;
    private String contactInfo;
    private PostStatus status;
    private Set<String> skills;
    private LocalDateTime createdAt;

    public MatchmakingPostResponse() {
    }

    public MatchmakingPostResponse(Long id, PostType postType, Long authorId, String authorName,
                                   String authorStudentCode, String authorAvatarUrl, Long teamId,
                                   String teamName, String title, String goals, DepartmentResponse department,
                                   FacultyResponse faculty, CohortResponse cohort, Integer slotsNeeded,
                                   String contactInfo, PostStatus status, Set<String> skills,
                                   LocalDateTime createdAt) {
        this.id = id;
        this.postType = postType;
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorStudentCode = authorStudentCode;
        this.authorAvatarUrl = authorAvatarUrl;
        this.teamId = teamId;
        this.teamName = teamName;
        this.title = title;
        this.goals = goals;
        this.department = department;
        this.faculty = faculty;
        this.cohort = cohort;
        this.slotsNeeded = slotsNeeded;
        this.contactInfo = contactInfo;
        this.status = status;
        this.skills = skills;
        this.createdAt = createdAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private PostType postType;
        private Long authorId;
        private String authorName;
        private String authorStudentCode;
        private String authorAvatarUrl;
        private Long teamId;
        private String teamName;
        private String title;
        private String goals;
        private DepartmentResponse department;
        private FacultyResponse faculty;
        private CohortResponse cohort;
        private Integer slotsNeeded;
        private String contactInfo;
        private PostStatus status;
        private Set<String> skills;
        private LocalDateTime createdAt;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder postType(PostType postType) {
            this.postType = postType;
            return this;
        }

        public Builder authorId(Long authorId) {
            this.authorId = authorId;
            return this;
        }

        public Builder authorName(String authorName) {
            this.authorName = authorName;
            return this;
        }

        public Builder authorStudentCode(String authorStudentCode) {
            this.authorStudentCode = authorStudentCode;
            return this;
        }

        public Builder authorAvatarUrl(String authorAvatarUrl) {
            this.authorAvatarUrl = authorAvatarUrl;
            return this;
        }

        public Builder teamId(Long teamId) {
            this.teamId = teamId;
            return this;
        }

        public Builder teamName(String teamName) {
            this.teamName = teamName;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder goals(String goals) {
            this.goals = goals;
            return this;
        }

        public Builder department(DepartmentResponse department) {
            this.department = department;
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

        public Builder slotsNeeded(Integer slotsNeeded) {
            this.slotsNeeded = slotsNeeded;
            return this;
        }

        public Builder contactInfo(String contactInfo) {
            this.contactInfo = contactInfo;
            return this;
        }

        public Builder status(PostStatus status) {
            this.status = status;
            return this;
        }

        public Builder skills(Set<String> skills) {
            this.skills = skills;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public MatchmakingPostResponse build() {
            return new MatchmakingPostResponse(id, postType, authorId, authorName, authorStudentCode,
                    authorAvatarUrl, teamId, teamName, title, goals, department, faculty, cohort,
                    slotsNeeded, contactInfo, status, skills, createdAt);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PostType getPostType() {
        return postType;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorStudentCode() {
        return authorStudentCode;
    }

    public void setAuthorStudentCode(String authorStudentCode) {
        this.authorStudentCode = authorStudentCode;
    }

    public String getAuthorAvatarUrl() {
        return authorAvatarUrl;
    }

    public void setAuthorAvatarUrl(String authorAvatarUrl) {
        this.authorAvatarUrl = authorAvatarUrl;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public DepartmentResponse getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentResponse department) {
        this.department = department;
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

    public Integer getSlotsNeeded() {
        return slotsNeeded;
    }

    public void setSlotsNeeded(Integer slotsNeeded) {
        this.slotsNeeded = slotsNeeded;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public PostStatus getStatus() {
        return status;
    }

    public void setStatus(PostStatus status) {
        this.status = status;
    }

    public Set<String> getSkills() {
        return skills;
    }

    public void setSkills(Set<String> skills) {
        this.skills = skills;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
