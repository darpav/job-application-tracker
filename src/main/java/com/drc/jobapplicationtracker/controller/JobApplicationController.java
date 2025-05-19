package com.drc.jobapplicationtracker.controller;

import com.drc.jobapplicationtracker.dto.JobApplicationDto;
import com.drc.jobapplicationtracker.model.JobApplication;
import com.drc.jobapplicationtracker.service.JobApplicationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    public JobApplicationController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }


    @GetMapping("/applications")
    public String getAllJobApplications(Model model) {
        List<JobApplication> jobApplications = jobApplicationService.getAllJobApplications();
        model.addAttribute("jobApplications", jobApplications);

        return "application/list";
    }

    @GetMapping("/applications/{id}")
    public String getJobApplicationDetail(@PathVariable("id") Long id) {
        jobApplicationService.getJobApplicationById(id);

        return "application/detail";
    }

    @GetMapping("/applications/new")
    public String showCreateForm(Model model) {
        JobApplicationDto jobApplicationDto = new JobApplicationDto();
        model.addAttribute("jobApplicationDto", jobApplicationDto);

        return "application/form";
    }

    @PostMapping("/applications/new")
    public String createJobApplication(@ModelAttribute("jobApplicationDto") JobApplicationDto jobApplicationDto) {
        jobApplicationService.createJobApplication(jobApplicationDto);

        return "redirect:/applications";
    }

    @GetMapping("/applications/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        JobApplicationDto jobApplicationDto = jobApplicationService.getJobApplicationById(id).get();
        model.addAttribute("jobApplicationDto", jobApplicationDto);

        return "application/form";
    }

    @PostMapping("/applications/edit/{id}")
    public String updateJobApplication(@ModelAttribute("jobApplicationDto") JobApplicationDto jobApplicationDto) {
        jobApplicationService.updateJobApplication(jobApplicationDto.getId(), jobApplicationDto);
        return "redirect:/applications";
    }

    @GetMapping("/applications/delete/{id}")
    public String deleteJobApplication(@PathVariable("id") Long id) {
        jobApplicationService.deleteJobApplication(id);
        return "redirect:/applications";
    }

    // export to pdf
}
