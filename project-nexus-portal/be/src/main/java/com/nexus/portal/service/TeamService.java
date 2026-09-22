package com.nexus.portal.service;

import com.nexus.portal.dto.request.TeamCreateRequest;
import com.nexus.portal.dto.request.TeamInvitationRequest;
import com.nexus.portal.dto.response.TeamInvitationResponse;
import com.nexus.portal.dto.response.TeamResponse;

import java.util.List;

public interface TeamService {
    TeamResponse createTeam(TeamCreateRequest request, Long leaderId);
    TeamResponse getMyTeam(Long userId, Long periodId);
    TeamResponse getTeamById(Long teamId);
    TeamResponse updateTeamName(Long teamId, String name, Long leaderId);
    TeamInvitationResponse sendInvitation(Long teamId, TeamInvitationRequest request, Long inviterId);
    List<TeamInvitationResponse> getMyInvitations(Long userId);
    TeamResponse respondToInvitation(Long invitationId, boolean accept, Long userId);
    TeamResponse removeMember(Long teamId, Long memberUserId, Long leaderId);
    void leaveTeam(Long teamId, Long userId);
    TeamResponse transferLeader(Long teamId, Long newLeaderUserId, Long currentLeaderId);
}
