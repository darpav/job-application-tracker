package com.drc.jobapplicationtracker.controller;

import com.drc.jobapplicationtracker.dto.CompanyJobDto;
import com.drc.jobapplicationtracker.service.CompanyJobService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CompanyJobController {

    private final CompanyJobService companyJobService;

    public CompanyJobController(CompanyJobService companyJobService) {
        this.companyJobService = companyJobService;
    }

    @GetMapping("/companyJobs")
    public String getAllCompanyJobs(Model model) {
        List<CompanyJobDto> companyJobsDto = companyJobService.getAllCompanyJobs();
        model.addAttribute("companyJobsDto", companyJobsDto);
        return "companyJob/list";
    }

    // get company by id

    @GetMapping("/companyJobs/new")
    public String showCreateForm(Model model) {
        CompanyJobDto companyJobDto = new CompanyJobDto();
        model.addAttribute("companyJobDto", companyJobDto);
        return "companyJob/form";
    }

    @PostMapping("/companyJobs/new")
    public String createCompanyJob(@ModelAttribute("companyJobDto") CompanyJobDto companyJobDto) {
        companyJobService.createCompanyJob(companyJobDto);
        return "redirect:/companyJobs";
    }

    @GetMapping("/companyJobs/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        CompanyJobDto companyJobDto = companyJobService.getCompanyJobById(id).get();
        model.addAttribute("companyJobDto", companyJobDto);
        return "companyJob/form";
    }

    @PostMapping("/companyJobs/edit/{id}")
    public String updateCompanyJob(@ModelAttribute("companyJobDto") CompanyJobDto companyJobDto) {
        companyJobService.updateCompanyJob(companyJobDto.getId(), companyJobDto);
        return "redirect:/companyJobs";
    }

    @GetMapping("/companyJobs/delete/{id}")
    public String deleteCompanyJob(@PathVariable("id") Long id) {
        companyJobService.deleteCompanyJob(id);
        return "redirect:/companyJobs";
    }
}
