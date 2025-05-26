package com.drc.jobapplicationtracker.service;

import com.drc.jobapplicationtracker.dto.CompanyJobDto;
import com.drc.jobapplicationtracker.model.CompanyJob;
import com.drc.jobapplicationtracker.model.JobApplication;
import com.drc.jobapplicationtracker.repository.CompanyJobRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyJobService {

    private final CompanyJobRepository companyJobRepository;
    private final ModelMapper modelMapper;

    public CompanyJobService(CompanyJobRepository companyJobRepository,
                             ModelMapper modelMapper) {
        this.companyJobRepository = companyJobRepository;
        this.modelMapper = modelMapper;
    }

    // get all
    public List<CompanyJobDto> getAllCompanyJobs() {
        List<CompanyJob> companyJobs = companyJobRepository.findAll();
        List<CompanyJobDto> companyJobsDto = new ArrayList<>();
        for (CompanyJob companyJob : companyJobs) {
            companyJobsDto.add(convertToDto(companyJob));
        }
        return companyJobsDto;
    }

    // get by id
    public Optional<CompanyJobDto> getCompanyJobById(Long id) {
        // check if exists by id
        // else throw exception
        Optional<CompanyJob> companyJobOptional = companyJobRepository.findById(id);
        return companyJobOptional.map(this::convertToDto);
    }

    // create
    public CompanyJob createCompanyJob(CompanyJobDto companyJobDto) {
        CompanyJob companyJob = convertToEntity(companyJobDto);
        return companyJobRepository.save(companyJob);
    }

    public CompanyJob updateCompanyJob(Long id, CompanyJobDto companyJobDto) {
        // if exists by id update
        // else throw exception
        CompanyJob companyJob = convertToEntity(companyJobDto);
        companyJob.setId(id);
        return companyJobRepository.save(companyJob);
    }

    // delete
    public void deleteCompanyJob(Long id) {
        // check if exists by id
        // else throw exception
        companyJobRepository.deleteById(id);
    }

    private CompanyJobDto convertToDto(CompanyJob companyJob) {
        return modelMapper.map(companyJob, CompanyJobDto.class);
    }

    private CompanyJob convertToEntity(CompanyJobDto companyJobDto) {
        return modelMapper.map(companyJobDto, CompanyJob.class);
    }
}
