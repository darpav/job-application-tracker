package com.drc.jobapplicationtracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobPostDto {

    private Long id;

    private String jobTitle;
    private String companyName;
    private String jobApplicationUrl;
    private LocalDate jobApplicationDeadline;

}
