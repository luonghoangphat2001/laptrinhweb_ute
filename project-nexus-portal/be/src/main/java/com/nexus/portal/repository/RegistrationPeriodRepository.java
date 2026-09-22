package com.nexus.portal.repository;

import com.nexus.portal.enums.PeriodStatus;
import com.nexus.portal.model.RegistrationPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrationPeriodRepository extends JpaRepository<RegistrationPeriod, Long> {

    List<RegistrationPeriod> findByStatus(PeriodStatus status);

    @Query("SELECT p FROM RegistrationPeriod p " +
           "JOIN p.targetFaculties f " +
           "JOIN p.targetCohorts c " +
           "WHERE p.status = 'OPEN' AND f.id = :facultyId AND c.id = :cohortId")
    List<RegistrationPeriod> findOpenPeriodsForStudent(@Param("facultyId") Long facultyId, @Param("cohortId") Long cohortId);
}
