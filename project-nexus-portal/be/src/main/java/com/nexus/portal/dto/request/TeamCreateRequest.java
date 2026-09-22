package com.nexus.portal.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Payload to create a new project team")
public class TeamCreateRequest {

    @NotBlank(message = "Team name is required")
    @Size(min = 3, max = 100, message = "Team name must be between 3 and 100 characters")
    @Schema(description = "Name of the student team", example = "Team Nexus Innovators", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotNull(message = "Registration Period ID is required")
    @Schema(description = "Active registration period ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long periodId;

    public TeamCreateRequest() {
    }

    public TeamCreateRequest(String name, Long periodId) {
        this.name = name;
        this.periodId = periodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPeriodId() {
        return periodId;
    }

    public void setPeriodId(Long periodId) {
        this.periodId = periodId;
    }
}
