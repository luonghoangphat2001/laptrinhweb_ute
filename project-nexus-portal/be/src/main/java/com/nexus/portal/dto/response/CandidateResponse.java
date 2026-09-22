package com.nexus.portal.dto.response;

import java.util.Set;

public class CandidateResponse {
    private Long userId;
    private String studentCode;
    private String fullName;
    private String email;
    private String phone;
    private String avatarUrl;
    private String faculty;
    private String cohort;
    private String major;
    private Long postId;
    private String postTitle;
    private String goals;
    private Set<String> skills;
    private boolean invitedByMyTeam;

    public CandidateResponse() {
    }

    public CandidateResponse(Long userId, String studentCode, String fullName, String email,
                             String phone, String avatarUrl, String faculty, String cohort,
                             String major, Long postId, String postTitle, String goals,
                             Set<String> skills, boolean invitedByMyTeam) {
        this.userId = userId;
        this.studentCode = studentCode;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.avatarUrl = avatarUrl;
        this.faculty = faculty;
        this.cohort = cohort;
        this.major = major;
        this.postId = postId;
        this.postTitle = postTitle;
        this.goals = goals;
        this.skills = skills;
        this.invitedByMyTeam = invitedByMyTeam;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getCohort() {
        return cohort;
    }

    public void setCohort(String cohort) {
        this.cohort = cohort;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public Set<String> getSkills() {
        return skills;
    }

    public void setSkills(Set<String> skills) {
        this.skills = skills;
    }

    public boolean isInvitedByMyTeam() {
        return invitedByMyTeam;
    }

    public void setInvitedByMyTeam(boolean invitedByMyTeam) {
        this.invitedByMyTeam = invitedByMyTeam;
    }
}
