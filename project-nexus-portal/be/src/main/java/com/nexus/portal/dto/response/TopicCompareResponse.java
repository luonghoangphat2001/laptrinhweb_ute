package com.nexus.portal.dto.response;

import java.util.List;

public class TopicCompareResponse {
    private List<TopicResponse> topics;

    public TopicCompareResponse() {
    }

    public TopicCompareResponse(List<TopicResponse> topics) {
        this.topics = topics;
    }

    public List<TopicResponse> getTopics() {
        return topics;
    }

    public void setTopics(List<TopicResponse> topics) {
        this.topics = topics;
    }
}
