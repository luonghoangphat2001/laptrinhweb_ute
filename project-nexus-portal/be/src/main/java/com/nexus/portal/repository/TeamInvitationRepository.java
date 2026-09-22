package com.nexus.portal.repository;

import com.nexus.portal.enums.InvitationStatus;
import com.nexus.portal.model.TeamInvitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamInvitationRepository extends JpaRepository<TeamInvitation, Long> {

    List<TeamInvitation> findByInviteeId(Long inviteeId);

    List<TeamInvitation> findByInviteeIdAndStatus(Long inviteeId, InvitationStatus status);

    List<TeamInvitation> findByTeamId(Long teamId);

    Optional<TeamInvitation> findByTeamIdAndInviteeIdAndStatus(Long teamId, Long inviteeId, InvitationStatus status);

    @Modifying
    @Query("UPDATE TeamInvitation ti SET ti.status = 'CANCELLED' WHERE ti.invitee.id = :inviteeId AND ti.status = 'PENDING'")
    void cancelAllPendingInvitationsForInvitee(@Param("inviteeId") Long inviteeId);
}
