package com.drc.jobapplicationtracker.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyJobDto {

    private Long id;

    private String companyName;
    private String companyUrl;
    private String companyCarersUrl;

}
