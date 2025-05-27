package com.drc.jobapplicationtracker.service;

import com.drc.jobapplicationtracker.dto.JobPortalDto;
import com.drc.jobapplicationtracker.model.AppUser;
import com.drc.jobapplicationtracker.model.JobPortal;
import com.drc.jobapplicationtracker.repository.AppUserRepository;
import com.drc.jobapplicationtracker.repository.JobPortalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobPortalService {

    private final JobPortalRepository jobPortalRepository;
    private final AppUserRepository appUserRepository;
    private final ModelMapper modelMapper;

    public JobPortalService(JobPortalRepository jobPortalRepository,
                            AppUserRepository appUserRepository,
                            ModelMapper modelMapper) {
        this.jobPortalRepository = jobPortalRepository;
        this.appUserRepository = appUserRepository;
        this.modelMapper = modelMapper;
    }

    public List<JobPortalDto> getAllJobPortals() {
        AppUser appUser = getAuthenticatedAppUser();

        List<JobPortal> jobPortals = jobPortalRepository.findByAppUserId(appUser.getId());
        List<JobPortalDto> jobPortalsDto = new ArrayList<>();

        for (JobPortal jobPortal : jobPortals) {
            jobPortalsDto.add(convertToDto(jobPortal));
        }

        return jobPortalsDto;
    }

    public Optional<JobPortalDto> getJobPortalById(Long id) {
        Optional<JobPortal> jobPortalOptional = jobPortalRepository.findById(id);
        return jobPortalOptional.map(this::convertToDto);
    }

    public JobPortal createJobPortal(JobPortalDto jobPortalDto) {
        if(jobPortalRepository.existsByUrl(jobPortalDto.getUrl())) {
            throw new RuntimeException("Job portal already exists with this url");
        }
        AppUser appUser = getAuthenticatedAppUser();

        JobPortal jobPortal = convertToEntity(jobPortalDto);
        jobPortal.setAppUser(appUser);
        return jobPortalRepository.save(jobPortal);
    }

    public JobPortal updateJobPortal(Long id, JobPortalDto jobPortalDto) {
        // if exists by id update
        // else throw exception
        JobPortal jobPortal = convertToEntity(jobPortalDto);
        jobPortal.setId(id);
        return jobPortalRepository.save(jobPortal);
    }

    public void deleteJobPortal(Long id) {
        // check if exists by id
        // else throw exception
        jobPortalRepository.deleteById(id);
    }

    private JobPortal convertToEntity(JobPortalDto jobPortalDto) {
        return modelMapper.map(jobPortalDto, JobPortal.class);
    }

    private JobPortalDto convertToDto(JobPortal jobPortal) {
        return modelMapper.map(jobPortal, JobPortalDto.class);
    }

    private AppUser getAuthenticatedAppUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        AppUser appUser = appUserRepository.findByUsername(user.getUsername()).get();

        return appUser;
    }
}
