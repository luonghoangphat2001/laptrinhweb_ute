package com.nexus.portal.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Payload to request joining a team from a matchmaking post")
public class TeamJoinRequestCreateRequest {

    @NotBlank(message = "Message is required")
    @Size(max = 1000, message = "Message cannot exceed 1000 characters")
    @Schema(description = "Self-introduction, strengths, and desire to join the team", example = "Hi! I am a fullstack student with experience in React and Spring Boot. I would love to collaborate.", requiredMode = Schema.RequiredMode.REQUIRED)
    private String message;

    public TeamJoinRequestCreateRequest() {
    }

    public TeamJoinRequestCreateRequest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
