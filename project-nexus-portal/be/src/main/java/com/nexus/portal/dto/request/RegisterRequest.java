package com.nexus.portal.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import com.nexus.portal.model.RoleName;

import java.util.Set;

@Schema(description = "User registration payload")
public class RegisterRequest {

    @NotBlank(message = "Username must not be blank")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    @Schema(description = "Unique username", example = "individual_user", minLength = 3, maxLength = 50)
    private String username;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email must be valid")
    @Schema(description = "Unique email address", example = "individual@nexus.local")
    private String email;

    @NotBlank(message = "Password must not be blank")
    @Size(min = 6, max = 100, message = "Password must be at least 6 characters")
    @Schema(description = "Password with minimum 6 characters", example = "password123")
    private String password;

    @NotBlank(message = "Full name must not be blank")
    @Schema(description = "Full name of the user", example = "Individual User")
    private String fullName;

    @Schema(description = "Set of roles to assign (optional)", example = "[\"ROLE_USER\"]")
    private Set<RoleName> roles;

    public RegisterRequest() {
    }

    public RegisterRequest(String username, String email, String password, String fullName, Set<RoleName> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<RoleName> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleName> roles) {
        this.roles = roles;
    }
}
