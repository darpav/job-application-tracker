package com.drc.jobapplicationtracker.service;

import com.drc.jobapplicationtracker.dto.CompanyCareerDto;
import com.drc.jobapplicationtracker.model.AppUser;
import com.drc.jobapplicationtracker.model.CompanyCareer;
import com.drc.jobapplicationtracker.repository.AppUserRepository;
import com.drc.jobapplicationtracker.repository.CompanyCareerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyCareerService {

    private final CompanyCareerRepository companyCareerRepository;
    private final AppUserRepository appUserRepository;
    private final ModelMapper modelMapper;

    public CompanyCareerService(CompanyCareerRepository companyCareerRepository,
                                AppUserRepository appUserRepository,
                                ModelMapper modelMapper) {
        this.companyCareerRepository = companyCareerRepository;
        this.appUserRepository = appUserRepository;
        this.modelMapper = modelMapper;

    }

    public List<CompanyCareerDto> getAllCompanyCareers() {
        List<CompanyCareer> companyCareers = companyCareerRepository.findAll();
        List<CompanyCareerDto> companyCareersDto = new ArrayList<>();
        for (CompanyCareer companyCareer : companyCareers) {
            companyCareersDto.add(convertToDto(companyCareer));
        }
        return companyCareersDto;
    }

    public Optional<CompanyCareerDto> getCompanyCareerById(Long id) {
        // check if exists by id
        // else throw exception
        Optional<CompanyCareer> companyCareerOptional = companyCareerRepository.findById(id);
        return companyCareerOptional.map(this::convertToDto);
    }

    public CompanyCareer createCompanyCareer(CompanyCareerDto companyCareerDto) {
        if(companyCareerRepository.existsByUrl(companyCareerDto.getUrl())) {
            throw new RuntimeException("Career already exists with this url");
        }

        AppUser appUser = getAuthenticatedAppUser();

        CompanyCareer companyCareer = convertToEntity(companyCareerDto);
        companyCareer.setAppUser(appUser);
        return companyCareerRepository.save(companyCareer);
    }

    public CompanyCareer updateCompanyCareer(Long id, CompanyCareerDto companyCareerDto) {
        // if exists by id update
        // else throw exception
        CompanyCareer companyCareer = convertToEntity(companyCareerDto);
        companyCareer.setId(id);
        return companyCareerRepository.save(companyCareer);
    }

    // delete
    public void deleteCompanyCareer(Long id) {
        // check if exists by id
        // else throw exception
        companyCareerRepository.deleteById(id);
    }

    private CompanyCareerDto convertToDto(CompanyCareer companyCareer) {
        return modelMapper.map(companyCareer, CompanyCareerDto.class);
    }

    private CompanyCareer convertToEntity(CompanyCareerDto companyCareerDto) {
        return modelMapper.map(companyCareerDto, CompanyCareer.class);
    }

    private AppUser getAuthenticatedAppUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        AppUser appUser = appUserRepository.findByUsername(user.getUsername()).get();

        return appUser;
    }
}
