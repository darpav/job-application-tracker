package com.drc.jobapplicationtracker.controller;

import com.drc.jobapplicationtracker.dto.CompanyCareerDto;
import com.drc.jobapplicationtracker.service.CompanyCareerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CompanyCareerController {

    private final CompanyCareerService companyCareerService;

    public CompanyCareerController(CompanyCareerService companyCareerService) {
        this.companyCareerService = companyCareerService;
    }

    @GetMapping("/company-careers")
    public String getAllCompanyCareers(Model model) {
        List<CompanyCareerDto> companyCareersDto = companyCareerService.getAllCompanyCareers();
        model.addAttribute("companyCareersDto", companyCareersDto);
        return "company-career/list";
    }

    @GetMapping("/company-careers/new")
    public String showCreateForm(Model model) {
        CompanyCareerDto companyCareerDto = new CompanyCareerDto();
        model.addAttribute("companyCareerDto", companyCareerDto);
        return "company-career/form";
    }

    @PostMapping("/company-careers/new")
    public String createCompanyCareer(@ModelAttribute("companyCareerDto") CompanyCareerDto companyCareerDto,
                                      Model model) {
        try {
            companyCareerService.createCompanyCareer(companyCareerDto);
            return "redirect:/company-careers";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("companyCareerDto", companyCareerDto);
            return "company-career/form";
        }
    }

    @GetMapping("/company-careers/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        CompanyCareerDto companyCareerDto = companyCareerService.getCompanyCareerById(id).get();
        model.addAttribute("companyCareerDto", companyCareerDto);
        return "company-career/form";
    }

    @PostMapping("/company-careers/edit/{id}")
    public String updateCompanyCareer(@ModelAttribute("companyCareerDto") CompanyCareerDto companyCareerDto) {
        companyCareerService.updateCompanyCareer(companyCareerDto.getId(), companyCareerDto);
        return "redirect:/company-careers";
    }

    @GetMapping("/company-careers/delete/{id}")
    public String deleteCompanyCareer(@PathVariable("id") Long id) {
        companyCareerService.deleteCompanyCareer(id);
        return "redirect:/company-careers";
    }
}
