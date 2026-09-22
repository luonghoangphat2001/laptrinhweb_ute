package com.nexus.portal.dto.response;

import com.nexus.portal.enums.TeamRole;

import java.time.LocalDateTime;

public class TeamMemberResponse {
    private Long memberRecordId;
    private Long userId;
    private String username;
    private String fullName;
    private String studentCode;
    private String avatarUrl;
    private String email;
    private String phone;
    private TeamRole roleInTeam;
    private LocalDateTime joinedAt;

    public TeamMemberResponse() {
    }

    public TeamMemberResponse(Long memberRecordId, Long userId, String username, String fullName,
                              String studentCode, String avatarUrl, String email, String phone,
                              TeamRole roleInTeam, LocalDateTime joinedAt) {
        this.memberRecordId = memberRecordId;
        this.userId = userId;
        this.username = username;
        this.fullName = fullName;
        this.studentCode = studentCode;
        this.avatarUrl = avatarUrl;
        this.email = email;
        this.phone = phone;
        this.roleInTeam = roleInTeam;
        this.joinedAt = joinedAt;
    }

    public Long getMemberRecordId() {
        return memberRecordId;
    }

    public void setMemberRecordId(Long memberRecordId) {
        this.memberRecordId = memberRecordId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
