package com.nexus.portal.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

@Schema(description = "User update request payload")
public class UserUpdateRequest {

    @NotBlank(message = "Full name must not be blank")
    @Schema(description = "Updated full name", example = "Jane Doe")
    private String fullName;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email must be valid")
    @Schema(description = "Updated email address", example = "jane.doe@example.com")
    private String email;

    @Schema(description = "Avatar image URL", example = "https://images.unsplash.com/photo-1494790108377-be9c29b29330")
    private String avatarUrl;

    @Schema(description = "Account active state", example = "true")
    private Boolean active;

    @Schema(description = "Assigned roles for the user", example = "[\"ROLE_USER\", \"ROLE_MANAGER\"]")
    private Set<String> roles;

    public UserUpdateRequest() {
    }

    public UserUpdateRequest(String fullName, String email, String avatarUrl, Boolean active, Set<String> roles) {
        this.fullName = fullName;
        this.email = email;
        this.avatarUrl = avatarUrl;
        this.active = active;
        this.roles = roles;
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

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
