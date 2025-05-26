package com.drc.jobapplicationtracker.service;

import com.drc.jobapplicationtracker.dto.JobPortalDto;
import com.drc.jobapplicationtracker.model.JobPortal;
import com.drc.jobapplicationtracker.repository.JobPortalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobPortalService {

    private final JobPortalRepository jobPortalRepository;
    private final ModelMapper modelMapper;

    public JobPortalService(JobPortalRepository jobPortalRepository, ModelMapper modelMapper) {
        this.jobPortalRepository = jobPortalRepository;
        this.modelMapper = modelMapper;
    }

    public JobPortal createJobPortal(JobPortalDto jobPortalDto) {
        JobPortal jobPortal = convertToEntity(jobPortalDto);
        return jobPortalRepository.save(jobPortal);
    }

    public List<JobPortalDto> getAllJobPortals() {
        List<JobPortal> jobPortals = jobPortalRepository.findAll();
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
}
