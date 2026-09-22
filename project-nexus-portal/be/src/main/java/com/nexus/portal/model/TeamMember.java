package com.nexus.portal.model;

import com.nexus.portal.enums.TeamRole;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "team_members", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"team_id", "user_id"})
})
public class TeamMember extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_in_team", length = 20, nullable = false)
    private TeamRole roleInTeam = TeamRole.MEMBER;

    @Column(name = "joined_at", nullable = false)
    private LocalDateTime joinedAt = LocalDateTime.now();

    public TeamMember() {
    }

    public TeamMember(Long id, Team team, User user, TeamRole roleInTeam, LocalDateTime joinedAt) {
        this.id = id;
        this.team = team;
        this.user = user;
        this.roleInTeam = roleInTeam != null ? roleInTeam : TeamRole.MEMBER;
        this.joinedAt = joinedAt != null ? joinedAt : LocalDateTime.now();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private Team team;
        private User user;
        private TeamRole roleInTeam = TeamRole.MEMBER;
        private LocalDateTime joinedAt = LocalDateTime.now();

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder team(Team team) {
            this.team = team;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder roleInTeam(TeamRole roleInTeam) {
            this.roleInTeam = roleInTeam;
            return this;
        }

        public Builder joinedAt(LocalDateTime joinedAt) {
            this.joinedAt = joinedAt;
            return this;
        }

        public TeamMember build() {
            return new TeamMember(id, team, user, roleInTeam, joinedAt);
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TeamRole getRoleInTeam() {
        return roleInTeam;
    }

    public void setRoleInTeam(TeamRole roleInTeam) {
        this.roleInTeam = roleInTeam;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(LocalDateTime joinedAt) {
        this.joinedAt = joinedAt;
    }
}
