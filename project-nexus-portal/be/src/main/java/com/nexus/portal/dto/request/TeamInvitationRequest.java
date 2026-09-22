package com.nexus.portal.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Payload to invite a student into the team")
public class TeamInvitationRequest {

    @NotNull(message = "Invitee user ID is required")
    @Schema(description = "User ID of the student being invited", example = "6", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long inviteeId;

    @Size(max = 255, message = "Invitation message cannot exceed 255 characters")
    @Schema(description = "Optional invitation message", example = "Hey! Would love to have you on our team for the AI Code Scanner thesis.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String message;

    public TeamInvitationRequest() {
    }

    public TeamInvitationRequest(Long inviteeId, String message) {
        this.inviteeId = inviteeId;
        this.message = message;
    }

    public Long getInviteeId() {
        return inviteeId;
    }

    public void setInviteeId(Long inviteeId) {
        this.inviteeId = inviteeId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
