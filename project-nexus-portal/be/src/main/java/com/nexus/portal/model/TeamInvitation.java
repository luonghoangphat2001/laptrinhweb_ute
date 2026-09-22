package com.nexus.portal.model;

import com.nexus.portal.enums.InvitationStatus;
import com.nexus.portal.enums.InvitationType;
import jakarta.persistence.*;

@Entity
@Table(name = "team_invitations")
public class TeamInvitation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inviter_id", nullable = false)
    private User inviter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invitee_id", nullable = false)
    private User invitee;

    @Column(name = "message", length = 255)
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 30, nullable = false)
    private InvitationType type = InvitationType.INVITE_BY_LEADER;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 30, nullable = false)
    private InvitationStatus status = InvitationStatus.PENDING;

    public TeamInvitation() {
    }

    public TeamInvitation(Long id, Team team, User inviter, User invitee, String message,
                          InvitationType type, InvitationStatus status) {
        this.id = id;
        this.team = team;
        this.inviter = inviter;
        this.invitee = invitee;
        this.message = message;
        this.type = type != null ? type : InvitationType.INVITE_BY_LEADER;
        this.status = status != null ? status : InvitationStatus.PENDING;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private Team team;
        private User inviter;
        private User invitee;
        private String message;
        private InvitationType type = InvitationType.INVITE_BY_LEADER;
        private InvitationStatus status = InvitationStatus.PENDING;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder team(Team team) {
            this.team = team;
            return this;
        }

        public Builder inviter(User inviter) {
            this.inviter = inviter;
            return this;
        }

        public Builder invitee(User invitee) {
            this.invitee = invitee;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder type(InvitationType type) {
            this.type = type;
            return this;
        }

        public Builder status(InvitationStatus status) {
            this.status = status;
            return this;
        }

        public TeamInvitation build() {
            return new TeamInvitation(id, team, inviter, invitee, message, type, status);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public User getInviter() {
        return inviter;
    }

    public void setInviter(User inviter) {
        this.inviter = inviter;
    }

    public User getInvitee() {
        return invitee;
    }

    public void setInvitee(User invitee) {
        this.invitee = invitee;
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
}
