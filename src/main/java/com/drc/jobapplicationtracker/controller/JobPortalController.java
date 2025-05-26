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

    @GetMapping("/jobPortals")
    public String getAllJobPortals(Model model) {
        List<JobPortalDto> jobPortalsDto = jobPortalService.getAllJobPortals();
        model.addAttribute("jobPortalsDto", jobPortalsDto);
        return "jobPortal/list";
    }

    @GetMapping("/jobPortals/new")
    public String showCreateForm(Model model) {
        JobPortalDto jobPortalDto = new JobPortalDto();
        model.addAttribute("jobPortalDto", jobPortalDto);
        return "jobPortal/form";
    }

    @PostMapping("/jobPortals/new")
    public String createJobPortal(@ModelAttribute("jobPortalDto") JobPortalDto jobPortalDto) {
        jobPortalService.createJobPortal(jobPortalDto);
        return "redirect:/jobPortals";
    }

    @GetMapping("/jobPortals/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        JobPortalDto jobPortalDto = jobPortalService.getJobPortalById(id).get();
        model.addAttribute("jobPortalDto", jobPortalDto);
        return "jobPortal/form";
    }

    @PostMapping("/jobPortals/edit/{id}")
    public String updateJobPortal(@ModelAttribute("jobPortalDto") JobPortalDto jobPortalDto) {
        jobPortalService.updateJobPortal(jobPortalDto.getId(), jobPortalDto);
        return "redirect:/jobPortals";
    }

    @GetMapping("/jobPortals/delete/{id}")
    public String deleteJobPortal(@PathVariable("id") Long id) {
        jobPortalService.deleteJobPortal(id);
        return "redirect:/jobPortals";
    }



}
