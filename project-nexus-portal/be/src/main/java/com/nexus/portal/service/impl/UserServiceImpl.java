package com.nexus.portal.service.impl;

import com.nexus.portal.dto.request.UserUpdateRequest;
import com.nexus.portal.dto.response.UserResponse;
import com.nexus.portal.model.Role;
import com.nexus.portal.model.RoleName;
import com.nexus.portal.model.User;
import com.nexus.portal.exception.BadRequestException;
import com.nexus.portal.exception.ResourceNotFoundException;
import com.nexus.portal.repository.RoleRepository;
import com.nexus.portal.repository.UserRepository;
import com.nexus.portal.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserResponse> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(this::mapToUserResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return mapToUserResponse(user);
    }

    @Override
    @Transactional
    public UserResponse updateUser(Long id, UserUpdateRequest updateRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        if (!user.getEmail().equalsIgnoreCase(updateRequest.getEmail()) &&
                userRepository.existsByEmail(updateRequest.getEmail())) {
            throw new BadRequestException("Email is already taken by another user");
        }

        user.setFullName(updateRequest.getFullName());
        user.setEmail(updateRequest.getEmail());
        if (updateRequest.getAvatarUrl() != null) {
            user.setAvatarUrl(updateRequest.getAvatarUrl());
        }
        if (updateRequest.getActive() != null) {
            user.setActive(updateRequest.getActive());
        }

        if (updateRequest.getRoles() != null && !updateRequest.getRoles().isEmpty()) {
            Set<Role> updatedRoles = new HashSet<>();
            for (RoleName roleName : updateRequest.getRoles()) {
                Role role = roleRepository.findByName(roleName)
                        .orElseThrow(() -> new ResourceNotFoundException("Role", "name", roleName));
                updatedRoles.add(role);
            }
            user.setRoles(updatedRoles);
        }

        User updatedUser = userRepository.save(user);
        return mapToUserResponse(updatedUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public void toggleUserStatus(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        user.setActive(!user.isActive());
        userRepository.save(user);
    }

    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .avatarUrl(user.getAvatarUrl())
                .active(user.isActive())
                .roles(user.getRoles().stream().map(r -> r.getName().name()).collect(Collectors.toSet()))
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
