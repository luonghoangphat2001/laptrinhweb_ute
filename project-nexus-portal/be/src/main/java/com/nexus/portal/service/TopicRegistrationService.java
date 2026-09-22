package com.nexus.portal.service;

import com.nexus.portal.dto.request.TopicRegistrationRequest;
import com.nexus.portal.dto.request.TopicReviewRequest;
import com.nexus.portal.dto.response.TopicRegistrationResponse;

import java.util.List;

public interface TopicRegistrationService {

    TopicRegistrationResponse registerTopic(TopicRegistrationRequest request, String currentUserEmail);

    TopicRegistrationResponse reviewRegistration(Long registrationId, TopicReviewRequest request, String reviewerEmail);

    List<TopicRegistrationResponse> getTeamRegistrations(Long teamId, String currentUserEmail);

    List<TopicRegistrationResponse> getTopicRegistrations(Long topicId, String currentUserEmail);

    List<TopicRegistrationResponse> getMyTeamRegistrations(String currentUserEmail);

    void cancelRegistration(Long registrationId, String currentUserEmail);
}
