package com.nexus.portal.controller;

import com.nexus.portal.dto.request.TeamCreateRequest;
import com.nexus.portal.dto.request.TeamInvitationRequest;
import com.nexus.portal.dto.response.ApiResponse;
import com.nexus.portal.dto.response.TeamInvitationResponse;
import com.nexus.portal.dto.response.TeamResponse;
import com.nexus.portal.exception.ResourceNotFoundException;
import com.nexus.portal.model.User;
import com.nexus.portal.repository.UserRepository;
import com.nexus.portal.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
@Tag(name = "Team Management", description = "Endpoints for creating and managing student teams, member rosters, and invitations")
public class TeamController {

    private final TeamService teamService;
    private final UserRepository userRepository;

    public TeamController(TeamService teamService, UserRepository userRepository) {
        this.teamService = teamService;
        this.userRepository = userRepository;
    }

    @PostMapping
    @Operation(summary = "Create a team", description = "Create a new team (1-3 members). The creator automatically becomes LEADER.")
    public ResponseEntity<ApiResponse<TeamResponse>> createTeam(
            @Valid @RequestBody TeamCreateRequest request,
            Authentication authentication) {
        User user = getCurrentUser(authentication);
        TeamResponse response = teamService.createTeam(request, user.getId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created(response, "Team created successfully"));
    }

    @GetMapping("/my-team")
    @Operation(summary = "Get my team", description = "Retrieve details and members of the current student's team for an active period")
    public ResponseEntity<ApiResponse<TeamResponse>> getMyTeam(
            @Parameter(description = "Optional Registration Period ID") @RequestParam(required = false) Long periodId,
            Authentication authentication) {
        User user = getCurrentUser(authentication);
        TeamResponse response = teamService.getMyTeam(user.getId(), periodId);
        return ResponseEntity.ok(ApiResponse.success(response, "Team details retrieved"));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get team by ID", description = "Retrieve public team details and roster by team ID")
    public ResponseEntity<ApiResponse<TeamResponse>> getTeamById(
            @Parameter(description = "Team ID", required = true) @PathVariable Long id) {
        TeamResponse response = teamService.getTeamById(id);
        return ResponseEntity.ok(ApiResponse.success(response, "Team retrieved"));
    }

    @PatchMapping("/{id}/name")
    @Operation(summary = "Update team name", description = "Team leader updates team name")
    public ResponseEntity<ApiResponse<TeamResponse>> updateTeamName(
            @Parameter(description = "Team ID", required = true) @PathVariable Long id,
            @Parameter(description = "New team name", required = true) @RequestParam String name,
            Authentication authentication) {
        User user = getCurrentUser(authentication);
        TeamResponse response = teamService.updateTeamName(id, name, user.getId());
        return ResponseEntity.ok(ApiResponse.success(response, "Team name updated"));
    }

    @PostMapping("/{id}/invitations")
    @Operation(summary = "Invite student to team", description = "Team leader invites an eligible student to join the team")
    public ResponseEntity<ApiResponse<TeamInvitationResponse>> inviteMember(
            @Parameter(description = "Team ID", required = true) @PathVariable Long id,
            @Valid @RequestBody TeamInvitationRequest request,
            Authentication authentication) {
        User user = getCurrentUser(authentication);
        TeamInvitationResponse response = teamService.sendInvitation(id, request, user.getId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created(response, "Invitation sent successfully"));
    }

    @GetMapping("/invitations/my-invitations")
    @Operation(summary = "Get my invitations", description = "Retrieve list of all pending team invitations received by current student")
    public ResponseEntity<ApiResponse<List<TeamInvitationResponse>>> getMyInvitations(Authentication authentication) {
        User user = getCurrentUser(authentication);
        List<TeamInvitationResponse> response = teamService.getMyInvitations(user.getId());
        return ResponseEntity.ok(ApiResponse.success(response, "Invitations retrieved"));
    }

    @PostMapping("/invitations/{invitationId}/respond")
    @Operation(summary = "Respond to team invitation", description = "Accept or reject a team invitation. Accepting auto-cancels other pending invites and join requests.")
    public ResponseEntity<ApiResponse<TeamResponse>> respondToInvitation(
            @Parameter(description = "Invitation ID", required = true) @PathVariable Long invitationId,
            @Parameter(description = "true to accept, false to reject", required = true) @RequestParam boolean accept,
            Authentication authentication) {
        User user = getCurrentUser(authentication);
        TeamResponse response = teamService.respondToInvitation(invitationId, accept, user.getId());
        return ResponseEntity.ok(ApiResponse.success(response, "Invitation response recorded"));
    }

    @DeleteMapping("/{id}/members/{memberUserId}")
    @Operation(summary = "Remove member from team", description = "Team leader removes a member from the team")
    public ResponseEntity<ApiResponse<TeamResponse>> removeMember(
            @Parameter(description = "Team ID", required = true) @PathVariable Long id,
            @Parameter(description = "Member User ID", required = true) @PathVariable Long memberUserId,
            Authentication authentication) {
        User user = getCurrentUser(authentication);
        TeamResponse response = teamService.removeMember(id, memberUserId, user.getId());
        return ResponseEntity.ok(ApiResponse.success(response, "Member removed from team"));
    }

    @PostMapping("/{id}/leave")
    @Operation(summary = "Leave team", description = "Team member leaves the team")
    public ResponseEntity<ApiResponse<Void>> leaveTeam(
            @Parameter(description = "Team ID", required = true) @PathVariable Long id,
            Authentication authentication) {
        User user = getCurrentUser(authentication);
        teamService.leaveTeam(id, user.getId());
        return ResponseEntity.ok(ApiResponse.success(null, "Left team successfully"));
    }

    @PostMapping("/{id}/transfer-leader")
    @Operation(summary = "Transfer team leadership", description = "Team leader transfers leadership to another member")
    public ResponseEntity<ApiResponse<TeamResponse>> transferLeader(
            @Parameter(description = "Team ID", required = true) @PathVariable Long id,
            @Parameter(description = "User ID of the new leader", required = true) @RequestParam Long newLeaderUserId,
            Authentication authentication) {
        User user = getCurrentUser(authentication);
        TeamResponse response = teamService.transferLeader(id, newLeaderUserId, user.getId());
        return ResponseEntity.ok(ApiResponse.success(response, "Team leadership transferred"));
    }

    private User getCurrentUser(Authentication authentication) {
        String principalName = authentication.getName();
        return userRepository.findByEmail(principalName)
                .orElseGet(() -> userRepository.findByUsername(principalName)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found: " + principalName)));
    }
}
