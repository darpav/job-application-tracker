package com.drc.jobapplicationtracker.repository;

import com.drc.jobapplicationtracker.model.CompanyJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyJobRepository extends JpaRepository<CompanyJob, Long> {
}
