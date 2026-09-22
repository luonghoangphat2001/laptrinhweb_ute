package com.nexus.portal.controller;

import com.nexus.portal.dto.request.MatchmakingPostCreateRequest;
import com.nexus.portal.dto.request.TeamJoinRequestCreateRequest;
import com.nexus.portal.dto.response.*;
import com.nexus.portal.enums.PostType;
import com.nexus.portal.enums.RequestStatus;
import com.nexus.portal.service.MatchmakingService;
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
@RequestMapping("/matchmaking")
@Tag(name = "Matchmaking & Collaboration", description = "Endpoints for student collaboration board, finding teammates, and team join requests")
public class MatchmakingController {

    private final MatchmakingService matchmakingService;

    public MatchmakingController(MatchmakingService matchmakingService) {
        this.matchmakingService = matchmakingService;
    }

    @GetMapping("/dashboard")
    @Operation(summary = "Get matchmaking dashboard", description = "Retrieve collaboration board posts and candidates automatically filtered by the current user's faculty and cohort")
    public ResponseEntity<ApiResponse<MatchmakingDashboardResponse>> getDashboard(Authentication authentication) {
        MatchmakingDashboardResponse dashboard = matchmakingService.getDashboard(authentication.getName());
        return ResponseEntity.ok(ApiResponse.success(dashboard, "Matchmaking dashboard retrieved"));
    }

    @GetMapping("/posts")
    @Operation(summary = "Search matchmaking posts", description = "Retrieve collaboration posts with multi-criteria filters")
    public ResponseEntity<ApiResponse<List<MatchmakingPostResponse>>> getPosts(
            @Parameter(description = "Post type: STUDENT_LOOKING_FOR_TEAM or TEAM_LOOKING_FOR_MEMBER") @RequestParam(required = false) PostType type,
            @Parameter(description = "Faculty ID filter") @RequestParam(required = false) Long facultyId,
            @Parameter(description = "Cohort ID filter") @RequestParam(required = false) Long cohortId,
            @Parameter(description = "Department ID filter") @RequestParam(required = false) Long departmentId,
            @Parameter(description = "Keyword or skill search") @RequestParam(required = false) String query) {

        List<MatchmakingPostResponse> posts = matchmakingService.getPosts(type, facultyId, cohortId, departmentId, query);
        return ResponseEntity.ok(ApiResponse.success(posts, "Posts retrieved successfully"));
    }

    @GetMapping("/posts/{id}")
    @Operation(summary = "Get matchmaking post details", description = "Retrieve detailed information for a matchmaking post")
    public ResponseEntity<ApiResponse<MatchmakingPostResponse>> getPostById(
            @Parameter(description = "Post ID", required = true) @PathVariable Long id) {
        MatchmakingPostResponse post = matchmakingService.getPostById(id);
        return ResponseEntity.ok(ApiResponse.success(post, "Post details retrieved"));
    }

    @PostMapping("/posts")
    @Operation(summary = "Create matchmaking post", description = "Publish a post to look for a team (student) or find members (team leader)")
    public ResponseEntity<ApiResponse<MatchmakingPostResponse>> createPost(
            @Valid @RequestBody MatchmakingPostCreateRequest request,
            Authentication authentication) {
        MatchmakingPostResponse post = matchmakingService.createPost(request, authentication.getName());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created(post, "Post published successfully"));
    }

    @PatchMapping("/posts/{id}/close")
    @Operation(summary = "Close matchmaking post", description = "Author or admin marks the post as CLOSED")
    public ResponseEntity<ApiResponse<Void>> closePost(
            @Parameter(description = "Post ID", required = true) @PathVariable Long id,
            Authentication authentication) {
        matchmakingService.closePost(id, authentication.getName());
        return ResponseEntity.ok(ApiResponse.success(null, "Post closed successfully"));
    }

    @GetMapping("/candidates")
    @Operation(summary = "Search candidates", description = "Browse individual students seeking teams, filtered by faculty, cohort, major or skills")
    public ResponseEntity<ApiResponse<List<CandidateResponse>>> getCandidates(
            @Parameter(description = "Faculty ID") @RequestParam(required = false) Long facultyId,
            @Parameter(description = "Cohort ID") @RequestParam(required = false) Long cohortId,
            @Parameter(description = "Major ID") @RequestParam(required = false) Long majorId,
            @Parameter(description = "Query string") @RequestParam(required = false) String query,
            Authentication authentication) {
        List<CandidateResponse> candidates = matchmakingService.getCandidates(facultyId, cohortId, majorId, query, authentication.getName());
        return ResponseEntity.ok(ApiResponse.success(candidates, "Candidates retrieved successfully"));
    }

    @PostMapping("/posts/{postId}/join-requests")
    @Operation(summary = "Apply to join a team", description = "Student submits a join request with a proposal/message to a team post")
    public ResponseEntity<ApiResponse<TeamJoinRequestResponse>> submitJoinRequest(
            @Parameter(description = "Target Team Post ID", required = true) @PathVariable Long postId,
            @Valid @RequestBody TeamJoinRequestCreateRequest request,
            Authentication authentication) {
        TeamJoinRequestResponse response = matchmakingService.submitJoinRequest(postId, request, authentication.getName());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created(response, "Join request submitted"));
    }

    @GetMapping("/posts/{postId}/join-requests")
    @Operation(summary = "Get join requests for post", description = "Team leader reviews join applications submitted for their post")
    public ResponseEntity<ApiResponse<List<TeamJoinRequestResponse>>> getPostJoinRequests(
            @Parameter(description = "Post ID", required = true) @PathVariable Long postId,
            Authentication authentication) {
        List<TeamJoinRequestResponse> responses = matchmakingService.getPostJoinRequests(postId, authentication.getName());
        return ResponseEntity.ok(ApiResponse.success(responses, "Join requests retrieved"));
    }

    @GetMapping("/join-requests/my-requests")
    @Operation(summary = "Get my submitted join requests", description = "Student views all team applications they have submitted")
    public ResponseEntity<ApiResponse<List<TeamJoinRequestResponse>>> getMyJoinRequests(Authentication authentication) {
        List<TeamJoinRequestResponse> responses = matchmakingService.getMyJoinRequests(authentication.getName());
        return ResponseEntity.ok(ApiResponse.success(responses, "Submitted join requests retrieved"));
    }

    @PutMapping("/join-requests/{requestId}/review")
    @Operation(summary = "Review team join request", description = "Team leader accepts or rejects an applicant. Accepting auto-cancels other pending invites and requests for this student.")
    public ResponseEntity<ApiResponse<TeamJoinRequestResponse>> reviewJoinRequest(
            @Parameter(description = "Join Request ID", required = true) @PathVariable Long requestId,
            @Parameter(description = "Decision: ACCEPTED or REJECTED", required = true) @RequestParam RequestStatus decision,
            Authentication authentication) {
        TeamJoinRequestResponse response = matchmakingService.reviewJoinRequest(requestId, decision, authentication.getName());
        return ResponseEntity.ok(ApiResponse.success(response, "Join request review recorded"));
    }
}
