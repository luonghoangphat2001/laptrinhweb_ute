package com.nexus.portal.service.impl;

import com.nexus.portal.dto.response.DashboardStatsResponse;
import com.nexus.portal.model.Role;
import com.nexus.portal.model.User;
import com.nexus.portal.repository.RoleRepository;
import com.nexus.portal.repository.UserRepository;
import com.nexus.portal.service.DashboardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.management.ManagementFactory;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public DashboardServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public DashboardStatsResponse getDashboardStats() {
        long totalUsers = userRepository.count();
        List<User> users = userRepository.findAll();
        long activeUsers = users.stream().filter(User::isActive).count();
        long totalRoles = roleRepository.count();

        Map<String, Long> roleDistribution = new HashMap<>();
        for (User user : users) {
            for (Role role : user.getRoles()) {
                String roleName = role.getName().name();
                roleDistribution.put(roleName, roleDistribution.getOrDefault(roleName, 0L) + 1);
            }
        }

        long uptimeSeconds = ManagementFactory.getRuntimeMXBean().getUptime() / 1000;

        List<Map<String, Object>> activities = new ArrayList<>();
        users.stream()
                .sorted(Comparator.comparing(User::getCreatedAt, Comparator.nullsLast(Comparator.reverseOrder())))
                .limit(5)
                .forEach(u -> {
                    Map<String, Object> item = new HashMap<>();
                    item.put("username", u.getUsername());
                    item.put("action", "User Registered");
                    item.put("timestamp", u.getCreatedAt() != null ? u.getCreatedAt() : LocalDateTime.now());
                    activities.add(item);
                });

        return DashboardStatsResponse.builder()
                .totalUsers(totalUsers)
                .activeUsers(activeUsers)
                .totalRoles(totalRoles)
                .systemUptimeSeconds(uptimeSeconds)
                .userRoleDistribution(roleDistribution)
                .recentActivities(activities)
                .build();
    }
}
