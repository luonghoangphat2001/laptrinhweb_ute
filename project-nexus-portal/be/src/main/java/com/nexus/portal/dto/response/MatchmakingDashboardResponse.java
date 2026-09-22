package com.nexus.portal.dto.response;

import java.util.List;

public class MatchmakingDashboardResponse {
    private List<MatchmakingPostResponse> posts;
    private List<TeamJoinRequestResponse> joinRequests;
    private long activePostsCount;
    private long openSlotsCount;

    public MatchmakingDashboardResponse() {
    }

    public MatchmakingDashboardResponse(List<MatchmakingPostResponse> posts, List<TeamJoinRequestResponse> joinRequests,
                                        long activePostsCount, long openSlotsCount) {
        this.posts = posts;
        this.joinRequests = joinRequests;
        this.activePostsCount = activePostsCount;
        this.openSlotsCount = openSlotsCount;
    }

    public List<MatchmakingPostResponse> getPosts() {
        return posts;
    }

    public void setPosts(List<MatchmakingPostResponse> posts) {
        this.posts = posts;
    }

    public List<TeamJoinRequestResponse> getJoinRequests() {
        return joinRequests;
    }

    public void setJoinRequests(List<TeamJoinRequestResponse> joinRequests) {
        this.joinRequests = joinRequests;
    }

    public long getActivePostsCount() {
        return activePostsCount;
    }

    public void setActivePostsCount(long activePostsCount) {
        this.activePostsCount = activePostsCount;
    }

    public long getOpenSlotsCount() {
        return openSlotsCount;
    }

    public void setOpenSlotsCount(long openSlotsCount) {
        this.openSlotsCount = openSlotsCount;
    }
}
