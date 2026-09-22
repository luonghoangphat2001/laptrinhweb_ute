package com.nexus.portal.service;

import com.nexus.portal.dto.request.UserUpdateRequest;
import com.nexus.portal.dto.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserResponse> getAllUsers(Pageable pageable);

    UserResponse getUserById(Long id);

    UserResponse updateUser(Long id, UserUpdateRequest updateRequest);

    void deleteUser(Long id);

    void toggleUserStatus(Long id);
    java.util.List<UserResponse> getScopedLecturers(String currentUserEmail);
}
