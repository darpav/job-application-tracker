package com.drc.jobapplicationtracker.repository;

import com.drc.jobapplicationtracker.model.JobPortal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPortalRepository extends JpaRepository<JobPortal, Long> {
}
