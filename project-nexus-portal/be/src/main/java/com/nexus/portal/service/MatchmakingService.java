package com.nexus.portal.service;

import com.nexus.portal.dto.request.MatchmakingPostCreateRequest;
import com.nexus.portal.dto.request.TeamJoinRequestCreateRequest;
import com.nexus.portal.dto.response.CandidateResponse;
import com.nexus.portal.dto.response.MatchmakingDashboardResponse;
import com.nexus.portal.dto.response.MatchmakingPostResponse;
import com.nexus.portal.dto.response.TeamJoinRequestResponse;
import com.nexus.portal.enums.PostType;
import com.nexus.portal.enums.RequestStatus;

import java.util.List;

public interface MatchmakingService {

    MatchmakingDashboardResponse getDashboard(String currentUserEmail);

    List<MatchmakingPostResponse> getPosts(PostType type, Long facultyId, Long cohortId, Long departmentId, String query);

    MatchmakingPostResponse getPostById(Long id);

    MatchmakingPostResponse createPost(MatchmakingPostCreateRequest request, String currentUserEmail);

    void closePost(Long id, String currentUserEmail);

    List<CandidateResponse> getCandidates(Long facultyId, Long cohortId, Long majorId, String query, String currentUserEmail);

    TeamJoinRequestResponse submitJoinRequest(Long postId, TeamJoinRequestCreateRequest request, String currentUserEmail);

    TeamJoinRequestResponse reviewJoinRequest(Long requestId, RequestStatus decision, String reviewerEmail);

    List<TeamJoinRequestResponse> getMyJoinRequests(String currentUserEmail);

    List<TeamJoinRequestResponse> getPostJoinRequests(Long postId, String currentUserEmail);
}
