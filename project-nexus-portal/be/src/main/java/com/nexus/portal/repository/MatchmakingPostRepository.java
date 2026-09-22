package com.nexus.portal.repository;

import com.nexus.portal.enums.PostStatus;
import com.nexus.portal.enums.PostType;
import com.nexus.portal.model.MatchmakingPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchmakingPostRepository extends JpaRepository<MatchmakingPost, Long> {

    List<MatchmakingPost> findByFacultyIdAndCohortIdAndStatus(Long facultyId, Long cohortId, PostStatus status);

    @Query("SELECT DISTINCT p FROM MatchmakingPost p " +
           "LEFT JOIN p.skills s " +
           "WHERE (:postType IS NULL OR p.postType = :postType) " +
           "AND (:facultyId IS NULL OR p.faculty.id = :facultyId) " +
           "AND (:cohortId IS NULL OR p.cohort.id = :cohortId) " +
           "AND (:departmentId IS NULL OR p.department.id = :departmentId) " +
           "AND (:status IS NULL OR p.status = :status) " +
           "AND (:skill IS NULL OR LOWER(s) LIKE LOWER(CONCAT('%', :skill, '%')))")
    Page<MatchmakingPost> searchPosts(
            @Param("postType") PostType postType,
            @Param("facultyId") Long facultyId,
            @Param("cohortId") Long cohortId,
            @Param("departmentId") Long departmentId,
            @Param("status") PostStatus status,
            @Param("skill") String skill,
            Pageable pageable
    );

    @Query("SELECT p FROM MatchmakingPost p " +
           "WHERE p.postType = 'STUDENT_LOOKING_FOR_TEAM' " +
           "AND p.status = 'OPEN' " +
           "AND (:facultyId IS NULL OR p.faculty.id = :facultyId) " +
           "AND (:cohortId IS NULL OR p.cohort.id = :cohortId)")
    List<MatchmakingPost> findCandidates(
            @Param("facultyId") Long facultyId,
            @Param("cohortId") Long cohortId
    );

    List<MatchmakingPost> findByAuthorId(Long authorId);
}
