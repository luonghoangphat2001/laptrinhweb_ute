package com.nexus.portal.repository;

import com.nexus.portal.enums.RequestStatus;
import com.nexus.portal.model.TeamJoinRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamJoinRequestRepository extends JpaRepository<TeamJoinRequest, Long> {

    List<TeamJoinRequest> findByPostId(Long postId);

    List<TeamJoinRequest> findByApplicantId(Long applicantId);

    List<TeamJoinRequest> findByApplicantIdAndStatus(Long applicantId, RequestStatus status);

    Optional<TeamJoinRequest> findByPostIdAndApplicantIdAndStatus(Long postId, Long applicantId, RequestStatus status);

    @Query("SELECT r FROM TeamJoinRequest r WHERE r.post.author.id = :authorId AND r.status = 'PENDING'")
    List<TeamJoinRequest> findPendingRequestsForAuthor(@Param("authorId") Long authorId);

    @Query("SELECT r FROM TeamJoinRequest r WHERE r.faculty.id = :facultyId AND r.cohort.id = :cohortId")
    List<TeamJoinRequest> findByFacultyIdAndCohortId(
            @Param("facultyId") Long facultyId,
            @Param("cohortId") Long cohortId
    );

    @Modifying
    @Query("UPDATE TeamJoinRequest r SET r.status = 'CANCELLED' WHERE r.applicant.id = :applicantId AND r.status = 'PENDING'")
    void cancelAllPendingRequestsForApplicant(@Param("applicantId") Long applicantId);
}
