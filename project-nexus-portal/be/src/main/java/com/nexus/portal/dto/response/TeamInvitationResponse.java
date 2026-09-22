package com.nexus.portal.dto.response;

import com.nexus.portal.enums.InvitationStatus;
import com.nexus.portal.enums.InvitationType;

import java.time.LocalDateTime;

public class TeamInvitationResponse {
    private Long id;
    private Long teamId;
    private String teamName;
    private Long inviterId;
    private String inviterName;
    private Long inviteeId;
    private String inviteeName;
    private String message;
    private InvitationType type;
    private InvitationStatus status;
    private LocalDateTime createdAt;

    public TeamInvitationResponse() {
    }

    public TeamInvitationResponse(Long id, Long teamId, String teamName, Long inviterId, String inviterName,
                                  Long inviteeId, String inviteeName, String message, InvitationType type,
                                  InvitationStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.teamId = teamId;
        this.teamName = teamName;
        this.inviterId = inviterId;
        this.inviterName = inviterName;
        this.inviteeId = inviteeId;
        this.inviteeName = inviteeName;
        this.message = message;
        this.type = type;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getInviterId() {
        return inviterId;
    }

    public void setInviterId(Long inviterId) {
        this.inviterId = inviterId;
    }

    public String getInviterName() {
        return inviterName;
    }

    public void setInviterName(String inviterName) {
        this.inviterName = inviterName;
    }

    public Long getInviteeId() {
        return inviteeId;
    }

    public void setInviteeId(Long inviteeId) {
        this.inviteeId = inviteeId;
    }

    public String getInviteeName() {
        return inviteeName;
    }

    public void setInviteeName(String inviteeName) {
        this.inviteeName = inviteeName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public InvitationType getType() {
        return type;
    }

    public void setType(InvitationType type) {
        this.type = type;
    }

    public InvitationStatus getStatus() {
        return status;
    }

    public void setStatus(InvitationStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
