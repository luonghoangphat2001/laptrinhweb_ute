package com.nexus.portal.service;

import com.nexus.portal.dto.request.TopicCreateRequest;
import com.nexus.portal.dto.response.TopicCompareResponse;
import com.nexus.portal.dto.response.TopicResponse;
import com.nexus.portal.enums.TopicStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TopicService {
    TopicResponse createTopic(TopicCreateRequest request, Long lecturerId);
    TopicResponse updateTopic(Long topicId, TopicCreateRequest request, Long lecturerId);
    void deleteTopic(Long topicId, Long lecturerId);
    TopicResponse toggleRegistration(Long topicId, Long lecturerId);
    List<TopicResponse> getLecturerTopics(Long lecturerId);
    List<TopicResponse> getTopicsByScope(String currentUserEmail);
    List<TopicResponse> getTopicsByDepartment(Long departmentId, String currentUserEmail);
    TopicResponse getTopicById(Long topicId);
    Page<TopicResponse> searchTopics(String keyword, Long departmentId, Long majorId, Long lecturerId, TopicStatus status, Long periodId, Pageable pageable);
    TopicCompareResponse compareTopics(List<Long> topicIds);
}
