package com.drc.jobapplicationtracker.repository;

import com.drc.jobapplicationtracker.model.JobPortal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobPortalRepository extends JpaRepository<JobPortal, Long> {
    boolean existsByUrl(String url);
    List<JobPortal> findByAppUserId(Long appUserId);
}
