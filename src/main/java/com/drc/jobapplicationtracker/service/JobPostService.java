package com.drc.jobapplicationtracker.service;

import com.drc.jobapplicationtracker.dto.JobPostDto;
import com.drc.jobapplicationtracker.model.JobPost;
import com.drc.jobapplicationtracker.repository.JobPostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobPostService {

    private final JobPostRepository jobPostRepository;
    private final ModelMapper modelMapper;

    public JobPostService(JobPostRepository jobPostRepository, ModelMapper modelMapper) {
        this.jobPostRepository = jobPostRepository;
        this.modelMapper = modelMapper;
    }

    public List<JobPostDto> getAllJobPosts(){
        List<JobPost> jobPosts = jobPostRepository.findAll();
        List<JobPostDto> jobPostsDto = new ArrayList<>();

        for(JobPost jobPost : jobPosts) {
            JobPostDto jobPostDto = convertToDto(jobPost);
            jobPostsDto.add(jobPostDto);
        }

        return jobPostsDto;
    }

    public Optional<JobPostDto> getJobPostById(Long id){
        Optional<JobPost> jobPostOptional = jobPostRepository.findById(id);
        return jobPostOptional.map(this::convertToDto);
    }

    public JobPost createJobPost(JobPostDto jobPostDto) {
        JobPost jobPost = convertToEntity(jobPostDto);
        return jobPostRepository.save(jobPost);
    }


    // update
    public JobPost updateJobPost(Long id, JobPostDto jobPostDto) {
        // if exists by id update
        // else throw exception
        JobPost jobPost = convertToEntity(jobPostDto);
        jobPost.setId(id);
        return jobPostRepository.save(jobPost);
    }

    // delete
    public void deleteJobPost(Long id) {
        // check if exists by id
        // else throw exception
        jobPostRepository.deleteById(id);
    }

    private JobPostDto convertToDto(JobPost jobPost) {
        return modelMapper.map(jobPost, JobPostDto.class);
    }

    private JobPost convertToEntity(JobPostDto jobPostDto) {
        return modelMapper.map(jobPostDto, JobPost.class);
    }
}
