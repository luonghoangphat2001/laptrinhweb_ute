package com.nexus.portal.repository;

import com.nexus.portal.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("SELECT tm.team FROM TeamMember tm WHERE tm.user.id = :userId AND tm.team.period.id = :periodId")
    Optional<Team> findByUserIdAndPeriodId(@Param("userId") Long userId, @Param("periodId") Long periodId);

    @Query("SELECT tm.team FROM TeamMember tm WHERE tm.user.id = :userId")
    List<Team> findAllTeamsByUserId(@Param("userId") Long userId);

    List<Team> findByPeriodId(Long periodId);
}
