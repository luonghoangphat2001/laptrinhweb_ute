package com.nexus.portal.repository;

import com.nexus.portal.model.TopicLecturer;
import com.nexus.portal.model.TopicLecturerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicLecturerRepository extends JpaRepository<TopicLecturer, TopicLecturerId> {
    List<TopicLecturer> findByTopicId(Long topicId);
    List<TopicLecturer> findByLecturerId(Long lecturerId);
    long countByTopicId(Long topicId);
}
