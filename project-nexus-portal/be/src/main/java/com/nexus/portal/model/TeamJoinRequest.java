package com.nexus.portal.model;

import com.nexus.portal.enums.RequestStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "team_join_requests")
public class TeamJoinRequest extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private MatchmakingPost post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_id", nullable = false)
    private User applicant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty faculty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cohort_id", nullable = false)
    private Cohort cohort;

    @Column(name = "message", columnDefinition = "TEXT")
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 30, nullable = false)
    private RequestStatus status = RequestStatus.PENDING;

    public TeamJoinRequest() {
    }

    public TeamJoinRequest(Long id, MatchmakingPost post, User applicant, Faculty faculty,
                           Cohort cohort, String message, RequestStatus status) {
        this.id = id;
        this.post = post;
        this.applicant = applicant;
        this.faculty = faculty;
        this.cohort = cohort;
        this.message = message;
        this.status = status != null ? status : RequestStatus.PENDING;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private MatchmakingPost post;
        private User applicant;
        private Faculty faculty;
        private Cohort cohort;
        private String message;
        private RequestStatus status = RequestStatus.PENDING;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder post(MatchmakingPost post) {
            this.post = post;
            return this;
        }

        public Builder applicant(User applicant) {
            this.applicant = applicant;
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

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder status(RequestStatus status) {
            this.status = status;
            return this;
        }

        public TeamJoinRequest build() {
            return new TeamJoinRequest(id, post, applicant, faculty, cohort, message, status);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MatchmakingPost getPost() {
        return post;
    }

    public void setPost(MatchmakingPost post) {
        this.post = post;
    }

    public User getApplicant() {
        return applicant;
    }

    public void setApplicant(User applicant) {
        this.applicant = applicant;
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
}
