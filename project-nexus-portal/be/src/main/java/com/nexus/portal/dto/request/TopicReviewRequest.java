package com.nexus.portal.dto.request;

import com.nexus.portal.enums.RegistrationStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Payload for lecturer reviewing team topic registration")
public class TopicReviewRequest {

    @NotNull(message = "Review status (APPROVED or REJECTED) is required")
    @Schema(description = "Decision outcome: APPROVED or REJECTED", example = "APPROVED", requiredMode = Schema.RequiredMode.REQUIRED)
    private RegistrationStatus status;

    @Size(max = 1000, message = "Feedback cannot exceed 1000 characters")
    @Schema(description = "Advisor feedback, comments, or rejection rationale", example = "Proposal accepted. Please prepare for kickoff meeting next Tuesday.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String feedback;

    public TopicReviewRequest() {
    }

    public TopicReviewRequest(RegistrationStatus status, String feedback) {
        this.status = status;
        this.feedback = feedback;
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
}
