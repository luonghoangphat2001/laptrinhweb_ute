package com.nexus.portal.dto.response;

import com.nexus.portal.enums.RegistrationStatus;

import java.time.LocalDateTime;

public class TopicRegistrationResponse {
    private Long id;
    private Long topicId;
    private String topicTitle;
    private Long teamId;
    private String teamName;
    private String message;
    private RegistrationStatus status;
    private String feedback;
    private LocalDateTime registeredAt;
    private LocalDateTime reviewedAt;
    private Long reviewedById;
    private String reviewedByName;

    public TopicRegistrationResponse() {
    }

    public TopicRegistrationResponse(Long id, Long topicId, String topicTitle, Long teamId,
                                   String teamName, String message, RegistrationStatus status,
                                   String feedback, LocalDateTime registeredAt, LocalDateTime reviewedAt,
                                   Long reviewedById, String reviewedByName) {
        this.id = id;
        this.topicId = topicId;
        this.topicTitle = topicTitle;
        this.teamId = teamId;
        this.teamName = teamName;
        this.message = message;
        this.status = status;
        this.feedback = feedback;
        this.registeredAt = registeredAt;
        this.reviewedAt = reviewedAt;
        this.reviewedById = reviewedById;
        this.reviewedByName = reviewedByName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RegistrationStatus getStatus() {
        return status;
    }

    public void setStatus(RegistrationStatus status) {
        this.status = status;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public LocalDateTime getReviewedAt() {
        return reviewedAt;
    }

    public void setReviewedAt(LocalDateTime reviewedAt) {
        this.reviewedAt = reviewedAt;
    }

    public Long getReviewedById() {
        return reviewedById;
    }

    public void setReviewedById(Long reviewedById) {
        this.reviewedById = reviewedById;
    }

    public String getReviewedByName() {
        return reviewedByName;
    }

    public void setReviewedByName(String reviewedByName) {
        this.reviewedByName = reviewedByName;
    }
}
