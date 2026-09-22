package com.nexus.portal.model;

import com.nexus.portal.enums.RegistrationStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "topic_registrations")
public class TopicRegistration extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @Column(name = "message", columnDefinition = "TEXT")
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 30, nullable = false)
    private RegistrationStatus status = RegistrationStatus.PENDING;

    @Column(name = "feedback", columnDefinition = "TEXT")
    private String feedback;

    @Column(name = "registered_at", nullable = false)
    private LocalDateTime registeredAt = LocalDateTime.now();

    @Column(name = "reviewed_at")
    private LocalDateTime reviewedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewed_by")
    private User reviewedBy;

    public TopicRegistration() {
    }

    public TopicRegistration(Long id, Topic topic, Team team, String message, RegistrationStatus status,
                             String feedback, LocalDateTime registeredAt, LocalDateTime reviewedAt, User reviewedBy) {
        this.id = id;
        this.topic = topic;
        this.team = team;
        this.message = message;
        this.status = status != null ? status : RegistrationStatus.PENDING;
        this.feedback = feedback;
        this.registeredAt = registeredAt != null ? registeredAt : LocalDateTime.now();
        this.reviewedAt = reviewedAt;
        this.reviewedBy = reviewedBy;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private Topic topic;
        private Team team;
        private String message;
        private RegistrationStatus status = RegistrationStatus.PENDING;
        private String feedback;
        private LocalDateTime registeredAt = LocalDateTime.now();
        private LocalDateTime reviewedAt;
        private User reviewedBy;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder topic(Topic topic) {
            this.topic = topic;
            return this;
        }

        public Builder team(Team team) {
            this.team = team;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder status(RegistrationStatus status) {
            this.status = status;
            return this;
        }

        public Builder feedback(String feedback) {
            this.feedback = feedback;
            return this;
        }

        public Builder registeredAt(LocalDateTime registeredAt) {
            this.registeredAt = registeredAt;
            return this;
        }

        public Builder reviewedAt(LocalDateTime reviewedAt) {
            this.reviewedAt = reviewedAt;
            return this;
        }

        public Builder reviewedBy(User reviewedBy) {
            this.reviewedBy = reviewedBy;
            return this;
        }

        public TopicRegistration build() {
            return new TopicRegistration(id, topic, team, message, status, feedback, registeredAt, reviewedAt, reviewedBy);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
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

    public User getReviewedBy() {
        return reviewedBy;
    }

    public void setReviewedBy(User reviewedBy) {
        this.reviewedBy = reviewedBy;
    }
}
