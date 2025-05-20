package com.drc.jobapplicationtracker.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobApplicationDto {

    private Long id;

    private String companyName;
    private String companyUrl;

    private String jobTitle;
    private String jobApplicationUrl;

    private String jobApplicationSource;
    private String jobApplicationSourceUrl;

    private LocalDate jobApplicationDate;
    private LocalDate jobApplicationDeadline;

    private String jobDescription;
    private String jobResponsibility;
    private String jobSkills;

    private String mailReceived;
    private String jobInterviewCall;

}
