package com.drc.jobapplicationtracker.service;

import com.drc.jobapplicationtracker.dto.JobApplicationDto;
import com.drc.jobapplicationtracker.model.AppUser;
import com.drc.jobapplicationtracker.model.JobApplication;
import com.drc.jobapplicationtracker.repository.AppUserRepository;
import com.drc.jobapplicationtracker.repository.JobApplicationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final AppUserRepository appUserRepository;
    private final ModelMapper modelMapper;

    public JobApplicationService(JobApplicationRepository jobApplicationRepository,
                                 AppUserRepository appUserRepository,
                                 ModelMapper modelMapper) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.appUserRepository = appUserRepository;
        this.modelMapper = modelMapper;
    }

    // get all Job Applications
    public List<JobApplicationDto> getAllJobApplications() {
        // return dto
        List<JobApplication> jobApplications = jobApplicationRepository.findAll();
        List<JobApplicationDto> jobApplicationsDto = new ArrayList<>();

        for (JobApplication jobApplication : jobApplications) {
            JobApplicationDto jobApplicationDto = convertToDto(jobApplication);
            // handle short description
            jobApplicationDto.setJobShortDescription(convertToShortDescription(jobApplication.getJobDescription()));
            jobApplicationsDto.add(jobApplicationDto);
        }

        return jobApplicationsDto;
    }

    public Optional<JobApplicationDto> getJobApplicationById(Long id) {
        Optional<JobApplication> jobApplicationOptional = jobApplicationRepository.findById(id);
        return jobApplicationOptional.map(this::convertToDto);
    }

    // save new Job Application
    public JobApplication createJobApplication(JobApplicationDto jobApplicationDto) {

        AppUser appUser = getAuthenticatedAppUser();

        JobApplication jobApplication = convertToEntity(jobApplicationDto);
        jobApplication.setAppUser(appUser);

        return jobApplicationRepository.save(jobApplication);
    }

    // update
    public JobApplication updateJobApplication(Long id, JobApplicationDto jobApplicationDto) {
        // if exists by id update
        // else throw exception

        JobApplication jobApplication = convertToEntity(jobApplicationDto);
        jobApplication.setId(id);
        return jobApplicationRepository.save(jobApplication);
    }

    // delete Job Application By id
    public void deleteJobApplication(Long id) {
        // if exists by id delete
        // else throw exception
        jobApplicationRepository.deleteById(id);
    }

    private JobApplication convertToEntity(JobApplicationDto jobApplicationDto) {
        return modelMapper.map(jobApplicationDto, JobApplication.class);
    }

    private JobApplicationDto convertToDto(JobApplication jobApplication) {
        return modelMapper.map(jobApplication, JobApplicationDto.class);
    }

    private String convertToShortDescription(String jobDescription) {
        String description = jobDescription;
        if(description.length() > 100) {
            description = description.substring(0, 100) + "...";
        }
        return description;
    }

    private AppUser getAuthenticatedAppUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        AppUser appUser = appUserRepository.findByUsername(user.getUsername()).get();

        return appUser;
    }
}
