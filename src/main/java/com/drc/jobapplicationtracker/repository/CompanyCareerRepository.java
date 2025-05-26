package com.drc.jobapplicationtracker.repository;

import com.drc.jobapplicationtracker.model.CompanyCareer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyCareerRepository extends JpaRepository<CompanyCareer, Long> {
    boolean existsByUrl(String url);
}
