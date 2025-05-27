package com.drc.jobapplicationtracker.repository;

import com.drc.jobapplicationtracker.model.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPostRepository extends JpaRepository<JobPost, Long> {

    boolean existsByJobApplicationUrl(String url);
}
