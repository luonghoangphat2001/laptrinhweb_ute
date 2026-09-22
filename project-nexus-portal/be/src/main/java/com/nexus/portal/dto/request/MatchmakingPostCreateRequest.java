package com.nexus.portal.dto.request;

import com.nexus.portal.enums.PostType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Schema(description = "Payload to publish a student matchmaking post")
public class MatchmakingPostCreateRequest {

    @NotNull(message = "Post type is required")
    @Schema(description = "STUDENT_LOOKING_FOR_TEAM or TEAM_LOOKING_FOR_MEMBER", example = "TEAM_LOOKING_FOR_MEMBER", requiredMode = Schema.RequiredMode.REQUIRED)
    private PostType postType;

    @Schema(description = "Team ID if this post is published on behalf of a team", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Long teamId;

    @NotBlank(message = "Post title is required")
    @Size(max = 200, message = "Title cannot exceed 200 characters")
    @Schema(description = "Short descriptive title", example = "Team Nexus looking for 1 Fullstack Developer", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @Schema(description = "Goals and focus area of the team/student", example = "Aiming for high performance AI capstone", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String goals;

    @Schema(description = "Optional department ID of interest", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Long departmentId;

    @Schema(description = "Number of open slots needed", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer slotsNeeded = 1;

    @NotBlank(message = "Contact info is required")
    @Size(max = 255, message = "Contact info cannot exceed 255 characters")
    @Schema(description = "Phone, telegram, or email contact", example = "student@nexus.edu.vn / 0901000004", requiredMode = Schema.RequiredMode.REQUIRED)
    private String contactInfo;

    @Schema(description = "Skills required or possessed", example = "[\"Spring Boot\", \"ReactJS\", \"Docker\"]", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Set<String> skills;

    public MatchmakingPostCreateRequest() {
    }

    public MatchmakingPostCreateRequest(PostType postType, Long teamId, String title, String goals,
                                        Long departmentId, Integer slotsNeeded, String contactInfo, Set<String> skills) {
        this.postType = postType;
        this.teamId = teamId;
        this.title = title;
        this.goals = goals;
        this.departmentId = departmentId;
        this.slotsNeeded = slotsNeeded != null ? slotsNeeded : 1;
        this.contactInfo = contactInfo;
        this.skills = skills;
    }

    public PostType getPostType() {
        return postType;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
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

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
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

    public Set<String> getSkills() {
        return skills;
    }

    public void setSkills(Set<String> skills) {
        this.skills = skills;
    }
}
