package com.drc.jobapplicationtracker.controller;

import com.drc.jobapplicationtracker.dto.JobApplicationDto;
import com.drc.jobapplicationtracker.model.JobApplication;
import com.drc.jobapplicationtracker.model.JobApplicationStage;
import com.drc.jobapplicationtracker.model.Status;
import com.drc.jobapplicationtracker.service.JobApplicationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    public JobApplicationController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }


    @GetMapping("/job-applications")
    public String getAllJobApplications(Model model) {
        List<JobApplicationDto> jobApplicationsDto = jobApplicationService.getAllJobApplications();
        model.addAttribute("jobApplicationsDto", jobApplicationsDto);

        return "job-application/list";
    }

    @GetMapping("/job-applications/{id}")
    public String getJobApplicationDetail(@PathVariable("id") Long id, Model model) {
        Optional<JobApplicationDto> jobApplicationDtoOptional = jobApplicationService.getJobApplicationById(id);

        if(jobApplicationDtoOptional.isPresent()) {
            JobApplicationDto jobApplicationDto = jobApplicationDtoOptional.get();
            model.addAttribute("jobApplicationDto", jobApplicationDto);
            return "job-application/detail";
        } else {
            return "error/not-found";
        }
    }

    @GetMapping("/job-applications/new")
    public String showCreateForm(Model model) {
        JobApplicationDto jobApplicationDto = new JobApplicationDto();
        model.addAttribute("jobApplicationDto", jobApplicationDto);
        model.addAttribute("statusOptions", Status.values());
        model.addAttribute("stageOptions", JobApplicationStage.values());

        return "job-application/form";
    }

    @PostMapping("/job-applications/new")
    public String createJobApplication(@ModelAttribute("jobApplicationDto") JobApplicationDto jobApplicationDto) {
        jobApplicationService.createJobApplication(jobApplicationDto);

        return "redirect:/job-applications";
    }

    @GetMapping("/job-applications/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        JobApplicationDto jobApplicationDto = jobApplicationService.getJobApplicationById(id).get();
        model.addAttribute("jobApplicationDto", jobApplicationDto);
        model.addAttribute("statusOptions", Status.values());
        model.addAttribute("stageOptions", JobApplicationStage.values());

        return "job-application/form";
    }

    @PostMapping("/job-applications/edit/{id}")
    public String updateJobApplication(@ModelAttribute("jobApplicationDto") JobApplicationDto jobApplicationDto) {
        jobApplicationService.updateJobApplication(jobApplicationDto.getId(), jobApplicationDto);
        return "redirect:/job-applications";
    }

    @GetMapping("/job-applications/delete/{id}")
    public String deleteJobApplication(@PathVariable("id") Long id) {
        jobApplicationService.deleteJobApplication(id);
        return "redirect:/job-applications";
    }

    // export to pdf
}
