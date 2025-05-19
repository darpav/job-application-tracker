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
    private String jobTitle;

    private LocalDate applicationDate;
    private LocalDate applicationDeadline;

    private String applicationSource;
    private String applicationUrl;

    private String jobDescription;

    private String mailReceived;
    private String jobInterviewCall;

    private String yourResponsibility;
    private String skillsNeeded;
    private String companyUrl;

    @Override
    public String toString() {
        return "JobApplicationDto{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", applicationDate=" + applicationDate +
                ", applicationDeadline=" + applicationDeadline +
                ", applicationSource='" + applicationSource + '\'' +
                ", applicationUrl='" + applicationUrl + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                ", mailReceived='" + mailReceived + '\'' +
                ", jobInterviewCall='" + jobInterviewCall + '\'' +
                '}';
    }
}
