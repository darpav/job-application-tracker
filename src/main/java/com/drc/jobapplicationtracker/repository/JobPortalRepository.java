package com.drc.jobapplicationtracker.repository;

import com.drc.jobapplicationtracker.model.JobPortal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobPortalRepository extends JpaRepository<JobPortal, Long> {
    boolean existsByUrl(String url);

    List<JobPortal> findByAppUserId(Long appUserId);
    Optional<JobPortal> findByIdAndAppUserId(Long id, Long appUserId);

    void deleteByIdAndAppUserId(Long id, Long appUserId);

    boolean existsByIdAndAppUserId(Long id, Long appUserId);
    boolean existsByUrlAndAppUserId(String url, Long appUserId);
}
