package com.nexus.portal.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Payload to register for a thesis topic")
public class TopicRegistrationRequest {

    @NotNull(message = "Topic ID is required")
    @Schema(description = "ID of the target topic", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long topicId;

    @Size(max = 1000, message = "Message cannot exceed 1000 characters")
    @Schema(description = "Optional message or proposal outline to the advisor", example = "Dear Professor, our team has reviewed the requirements and prepared a preliminary architecture.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String message;

    public TopicRegistrationRequest() {
    }

    public TopicRegistrationRequest(Long topicId, String message) {
        this.topicId = topicId;
        this.message = message;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
