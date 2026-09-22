package com.nexus.portal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TopicLecturerId implements Serializable {

    @Column(name = "topic_id")
    private Long topicId;

    @Column(name = "lecturer_id")
    private Long lecturerId;

    public TopicLecturerId() {
    }

    public TopicLecturerId(Long topicId, Long lecturerId) {
        this.topicId = topicId;
        this.lecturerId = lecturerId;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public Long getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(Long lecturerId) {
        this.lecturerId = lecturerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopicLecturerId that = (TopicLecturerId) o;
        return Objects.equals(topicId, that.topicId) && Objects.equals(lecturerId, that.lecturerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topicId, lecturerId);
    }
}
