package com.drc.jobapplicationtracker.controller;

import com.drc.jobapplicationtracker.dto.JobPostDto;
import com.drc.jobapplicationtracker.service.JobPostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class JobPostController {

    private final JobPostService jobPostService;

    public JobPostController(JobPostService jobPostService) {
        this.jobPostService = jobPostService;
    }

    @GetMapping("/job-posts")
    public String getAllJobPosts(Model model) {
        // get all job posts
        List<JobPostDto> jobPostsDto = jobPostService.getAllJobPosts();
        model.addAttribute("jobPostsDto", jobPostsDto);

        return "job-post/list";
    }

    @GetMapping("/job-posts/new")
    public String showCreateForm(Model model) {
        JobPostDto jobPostDto = new JobPostDto();
        model.addAttribute("jobPostDto", jobPostDto);
        return "job-post/form";
    }

    @PostMapping("/job-posts/new")
    public String createJobPost(@ModelAttribute("jobPostDto") JobPostDto jobPostDto,
                                Model model) {
        try {
            jobPostService.createJobPost(jobPostDto);
            return "redirect:/job-posts";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("jobPostDto", jobPostDto);
            return "job-post/form";
        }
    }

    @GetMapping("/job-posts/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        JobPostDto jobPostDto = jobPostService.getJobPostById(id).get();
        model.addAttribute("jobPostDto", jobPostDto);
        return "job-post/form";
    }

    @PostMapping("/job-posts/edit/{id}")
    public String updateJobPost(@ModelAttribute("jobPostDto") JobPostDto jobPostDto) {
        jobPostService.updateJobPost(jobPostDto.getId(), jobPostDto);
        return "redirect:/job-posts";
    }

    @GetMapping("/job-posts/delete/{id}")
    public String deleteJobPost(@PathVariable("id") Long id) {
        jobPostService.deleteJobPost(id);
        return "redirect:/job-posts";
    }


}
