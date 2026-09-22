package com.nexus.portal.dto.response;

import com.nexus.portal.enums.RequestStatus;

import java.time.LocalDateTime;

public class TeamJoinRequestResponse {
    private Long id;
    private Long postId;
    private String postTitle;
    private Long applicantId;
    private String applicantName;
    private String applicantStudentCode;
    private String applicantAvatarUrl;
    private String applicantEmail;
    private String applicantPhone;
    private FacultyResponse faculty;
    private CohortResponse cohort;
    private String message;
    private RequestStatus status;
    private LocalDateTime createdAt;

    public TeamJoinRequestResponse() {
    }

    public TeamJoinRequestResponse(Long id, Long postId, String postTitle, Long applicantId,
                                  String applicantName, String applicantStudentCode, String applicantAvatarUrl,
                                  String applicantEmail, String applicantPhone, FacultyResponse faculty,
                                  CohortResponse cohort, String message, RequestStatus status,
                                  LocalDateTime createdAt) {
        this.id = id;
        this.postId = postId;
        this.postTitle = postTitle;
        this.applicantId = applicantId;
        this.applicantName = applicantName;
        this.applicantStudentCode = applicantStudentCode;
        this.applicantAvatarUrl = applicantAvatarUrl;
        this.applicantEmail = applicantEmail;
        this.applicantPhone = applicantPhone;
        this.faculty = faculty;
        this.cohort = cohort;
        this.message = message;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Long applicantId) {
        this.applicantId = applicantId;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicantStudentCode() {
        return applicantStudentCode;
    }

    public void setApplicantStudentCode(String applicantStudentCode) {
        this.applicantStudentCode = applicantStudentCode;
    }

    public String getApplicantAvatarUrl() {
        return applicantAvatarUrl;
    }

    public void setApplicantAvatarUrl(String applicantAvatarUrl) {
        this.applicantAvatarUrl = applicantAvatarUrl;
    }

    public String getApplicantEmail() {
        return applicantEmail;
    }

    public void setApplicantEmail(String applicantEmail) {
        this.applicantEmail = applicantEmail;
    }

    public String getApplicantPhone() {
        return applicantPhone;
    }

    public void setApplicantPhone(String applicantPhone) {
        this.applicantPhone = applicantPhone;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
