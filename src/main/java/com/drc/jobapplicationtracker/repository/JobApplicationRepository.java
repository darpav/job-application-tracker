package com.drc.jobapplicationtracker.repository;

import com.drc.jobapplicationtracker.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

    // find all by appUserId order by dateDeadline
    List<JobApplication> findAllByAppUserIdOrderByJobApplicationDeadlineAsc(Long appUserId);

    // select all by appUserId, status active, sort by dateDeadline


}
