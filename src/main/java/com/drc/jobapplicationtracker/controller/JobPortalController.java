package com.drc.jobapplicationtracker.controller;

import com.drc.jobapplicationtracker.dto.JobPortalDto;
import com.drc.jobapplicationtracker.service.JobPortalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class JobPortalController {

    private final JobPortalService jobPortalService;

    public JobPortalController(JobPortalService jobPortalService) {
        this.jobPortalService = jobPortalService;
    }

    @GetMapping("/job-portals")
    public String getAllJobPortals(Model model) {
        List<JobPortalDto> jobPortalsDto = jobPortalService.getAllJobPortals();
        model.addAttribute("jobPortalsDto", jobPortalsDto);
        return "job-portal/list";
    }

    @GetMapping("/job-portals/new")
    public String showCreateForm(Model model) {
        JobPortalDto jobPortalDto = new JobPortalDto();
        model.addAttribute("jobPortalDto", jobPortalDto);
        return "job-portal/form";
    }

    @PostMapping("/job-portals/new")
    public String createJobPortal(@ModelAttribute("jobPortalDto") JobPortalDto jobPortalDto,
                                  Model model) {
        try {
            jobPortalService.createJobPortal(jobPortalDto);
            return "redirect:/job-portals";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("jobPortalDto", jobPortalDto);
            return "job-portal/form";
        }

    }

    @GetMapping("/job-portals/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        JobPortalDto jobPortalDto = jobPortalService.getJobPortalById(id).get();
        model.addAttribute("jobPortalDto", jobPortalDto);
        return "job-portal/form";
    }

    @PostMapping("/job-portals/edit/{id}")
    public String updateJobPortal(@ModelAttribute("jobPortalDto") JobPortalDto jobPortalDto) {
        jobPortalService.updateJobPortal(jobPortalDto.getId(), jobPortalDto);
        return "redirect:/job-portals";
    }

    @GetMapping("/job-portals/delete/{id}")
    public String deleteJobPortal(@PathVariable("id") Long id) {
        jobPortalService.deleteJobPortal(id);
        return "redirect:/job-portals";
    }

    

}
