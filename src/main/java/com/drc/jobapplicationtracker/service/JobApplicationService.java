package com.drc.jobapplicationtracker.service;

import com.drc.jobapplicationtracker.dto.JobApplicationDto;
import com.drc.jobapplicationtracker.model.JobApplication;
import com.drc.jobapplicationtracker.repository.JobApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;

    public JobApplicationService(JobApplicationRepository jobApplicationRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
    }

    // get all Job Applications
    public List<JobApplication> getAllJobApplications() {
        return jobApplicationRepository.findAll();
    }

    // get Job Application by id
    public Optional<JobApplicationDto> getJobApplicationById(Long id) {

        JobApplicationDto jobApplicationDto = new JobApplicationDto();
        Optional<JobApplication> jobApplicationOptional = jobApplicationRepository.findById(id);

        if(jobApplicationOptional.isPresent()) {
            jobApplicationDto = mapToJobApplicationDto(jobApplicationOptional.get());
        }

        return Optional.of(jobApplicationDto);
    }

    // save new Job Application
    public JobApplication createJobApplication(JobApplicationDto jobApplicationDto) {
        JobApplication jobApplication = mapToJobApplication(jobApplicationDto);
        return jobApplicationRepository.save(jobApplication);
    }

    // update
    public JobApplication updateJobApplication(Long id, JobApplicationDto jobApplicationDto) {
        // if exists by id update
        // else throw exception

        JobApplication jobApplication = mapToJobApplication(jobApplicationDto);
        jobApplication.setId(id);
        return jobApplicationRepository.save(jobApplication);
    }

    // delete Job Application By id
    public void deleteJobApplication(Long id) {
        // if exists by id delete
        // else throw exception
        jobApplicationRepository.deleteById(id);
    }

    private JobApplication mapToJobApplication(JobApplicationDto jobApplicationDto) {
        JobApplication jobApplication = new JobApplication();

        jobApplication.setCompanyName(jobApplicationDto.getCompanyName());
        jobApplication.setJobTitle(jobApplicationDto.getJobTitle());
        jobApplication.setApplicationDate(jobApplicationDto.getApplicationDate());
        jobApplication.setApplicationDeadline(jobApplicationDto.getApplicationDeadline());
        jobApplication.setApplicationSource(jobApplicationDto.getApplicationSource());
        jobApplication.setApplicationUrl(jobApplicationDto.getApplicationUrl());
        jobApplication.setJobDescription(jobApplicationDto.getJobDescription());
        jobApplication.setMailReceived(jobApplicationDto.getMailReceived());
        jobApplication.setJobInterviewCall(jobApplicationDto.getJobInterviewCall());

        return jobApplication;
    }

    private JobApplicationDto mapToJobApplicationDto(JobApplication jobApplication) {
        JobApplicationDto jobApplicationDto = new JobApplicationDto();

        jobApplicationDto.setId(jobApplication.getId());
        jobApplicationDto.setCompanyName(jobApplication.getCompanyName());
        jobApplicationDto.setJobTitle(jobApplication.getJobTitle());
        jobApplicationDto.setApplicationDate(jobApplication.getApplicationDate());
        jobApplicationDto.setApplicationDeadline(jobApplication.getApplicationDeadline());
        jobApplicationDto.setApplicationSource(jobApplication.getApplicationSource());
        jobApplicationDto.setApplicationUrl(jobApplication.getApplicationUrl());
        jobApplicationDto.setJobDescription(jobApplication.getJobDescription());
        jobApplicationDto.setMailReceived(jobApplication.getMailReceived());
        jobApplicationDto.setJobInterviewCall(jobApplication.getJobInterviewCall());

        return jobApplicationDto;
    }
}
