package com.nexus.portal.model;

import com.nexus.portal.enums.AdvisorRole;
import jakarta.persistence.*;

@Entity
@Table(name = "topic_lecturers")
public class TopicLecturer extends BaseEntity {

    @EmbeddedId
    private TopicLecturerId id = new TopicLecturerId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("topicId")
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("lecturerId")
    @JoinColumn(name = "lecturer_id", nullable = false)
    private User lecturer;

    @Enumerated(EnumType.STRING)
    @Column(name = "advisor_role", length = 20, nullable = false)
    private AdvisorRole advisorRole = AdvisorRole.PRIMARY;

    public TopicLecturer() {
    }

    public TopicLecturer(Topic topic, User lecturer, AdvisorRole advisorRole) {
        this.topic = topic;
        this.lecturer = lecturer;
        this.advisorRole = advisorRole != null ? advisorRole : AdvisorRole.PRIMARY;
        this.id = new TopicLecturerId(topic != null ? topic.getId() : null, lecturer != null ? lecturer.getId() : null);
    }

    public TopicLecturerId getId() {
        return id;
    }

    public void setId(TopicLecturerId id) {
        this.id = id;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
        if (topic != null) {
            this.id.setTopicId(topic.getId());
        }
    }

    public User getLecturer() {
        return lecturer;
    }

    public void setLecturer(User lecturer) {
        this.lecturer = lecturer;
        if (lecturer != null) {
            this.id.setLecturerId(lecturer.getId());
        }
    }

    public AdvisorRole getAdvisorRole() {
        return advisorRole;
    }

    public void setAdvisorRole(AdvisorRole advisorRole) {
        this.advisorRole = advisorRole;
    }
}
