package com.drc.jobapplicationtracker.dto;

import com.drc.jobapplicationtracker.model.JobApplicationStage;
import com.drc.jobapplicationtracker.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobApplicationDto {

    private Long id;

    private String jobTitle;
    private String jobApplicationUrl;

    private String companyName;
    private String companyUrl;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate jobApplicationDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate jobApplicationDeadline;

    private String jobDescription;
    private String jobSkills;

    private String jobShortDescription;

    private Status status;

    private String confirmationMailReceived;
    private JobApplicationStage jobApplicationStage;

}
