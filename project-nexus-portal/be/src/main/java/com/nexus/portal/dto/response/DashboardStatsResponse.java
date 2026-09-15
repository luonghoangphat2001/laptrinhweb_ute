package com.nexus.portal.dto.response;

import java.util.List;
import java.util.Map;

public class DashboardStatsResponse {
    private long totalUsers;
    private long activeUsers;
    private long totalRoles;
    private long systemUptimeSeconds;
    private List<Map<String, Object>> recentActivities;
    private Map<String, Long> userRoleDistribution;

    public DashboardStatsResponse() {
    }

    public DashboardStatsResponse(long totalUsers, long activeUsers, long totalRoles, long systemUptimeSeconds, List<Map<String, Object>> recentActivities, Map<String, Long> userRoleDistribution) {
        this.totalUsers = totalUsers;
        this.activeUsers = activeUsers;
        this.totalRoles = totalRoles;
        this.systemUptimeSeconds = systemUptimeSeconds;
        this.recentActivities = recentActivities;
        this.userRoleDistribution = userRoleDistribution;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private long totalUsers;
        private long activeUsers;
        private long totalRoles;
        private long systemUptimeSeconds;
        private List<Map<String, Object>> recentActivities;
        private Map<String, Long> userRoleDistribution;

        public Builder totalUsers(long totalUsers) {
            this.totalUsers = totalUsers;
            return this;
        }

        public Builder activeUsers(long activeUsers) {
            this.activeUsers = activeUsers;
            return this;
        }

        public Builder totalRoles(long totalRoles) {
            this.totalRoles = totalRoles;
            return this;
        }

        public Builder systemUptimeSeconds(long systemUptimeSeconds) {
            this.systemUptimeSeconds = systemUptimeSeconds;
            return this;
        }

        public Builder recentActivities(List<Map<String, Object>> recentActivities) {
            this.recentActivities = recentActivities;
            return this;
        }

        public Builder userRoleDistribution(Map<String, Long> userRoleDistribution) {
            this.userRoleDistribution = userRoleDistribution;
            return this;
        }

        public DashboardStatsResponse build() {
            return new DashboardStatsResponse(totalUsers, activeUsers, totalRoles, systemUptimeSeconds, recentActivities, userRoleDistribution);
        }
    }

    public long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public long getActiveUsers() {
        return activeUsers;
    }

    public void setActiveUsers(long activeUsers) {
        this.activeUsers = activeUsers;
    }

    public long getTotalRoles() {
        return totalRoles;
    }

    public void setTotalRoles(long totalRoles) {
        this.totalRoles = totalRoles;
    }

    public long getSystemUptimeSeconds() {
        return systemUptimeSeconds;
    }

    public void setSystemUptimeSeconds(long systemUptimeSeconds) {
        this.systemUptimeSeconds = systemUptimeSeconds;
    }

    public List<Map<String, Object>> getRecentActivities() {
        return recentActivities;
    }

    public void setRecentActivities(List<Map<String, Object>> recentActivities) {
        this.recentActivities = recentActivities;
    }

    public Map<String, Long> getUserRoleDistribution() {
        return userRoleDistribution;
    }

    public void setUserRoleDistribution(Map<String, Long> userRoleDistribution) {
        this.userRoleDistribution = userRoleDistribution;
    }
}
