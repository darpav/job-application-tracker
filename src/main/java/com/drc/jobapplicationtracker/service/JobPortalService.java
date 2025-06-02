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
        AppUser appUser = getAuthenticatedAppUser();
        Optional<JobPortal> jobPortalOptional = jobPortalRepository.findByIdAndAppUserId(id, appUser.getId());
        return jobPortalOptional.map(this::convertToDto);
    }

    public JobPortal createJobPortal(JobPortalDto jobPortalDto) {
        AppUser appUser = getAuthenticatedAppUser();

        if(jobPortalRepository.existsByUrlAndAppUserId(jobPortalDto.getUrl(), appUser.getId())) {
            throw new RuntimeException("Job portal already exists with this url");
        }

        JobPortal jobPortal = convertToEntity(jobPortalDto);
        jobPortal.setAppUser(appUser);
        return jobPortalRepository.save(jobPortal);
    }

    public JobPortal updateJobPortal(Long id, JobPortalDto jobPortalDto) {
        AppUser appUser = getAuthenticatedAppUser();

        if(!jobPortalRepository.existsByIdAndAppUserId(id, appUser.getId())) {
            throw new RuntimeException("Job portal does not exist with this id");
        }

        JobPortal jobPortal = convertToEntity(jobPortalDto);
        jobPortal.setId(id);
        jobPortal.setAppUser(appUser);
        return jobPortalRepository.save(jobPortal);
    }

    public void deleteJobPortal(Long id) {
        AppUser appUser = getAuthenticatedAppUser();

        if(!jobPortalRepository.existsByIdAndAppUserId(id, appUser.getId())) {
            throw new RuntimeException("Job portal does not exist with this id");
        }

        jobPortalRepository.deleteByIdAndAppUserId(id, appUser.getId());
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
