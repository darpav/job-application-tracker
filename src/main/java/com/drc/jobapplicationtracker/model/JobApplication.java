package com.drc.jobapplicationtracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "job_applications")
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name")
    private String companyName;
    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "application_date")
    private LocalDate applicationDate;
    @Column(name = "application_deadline")
    private LocalDate applicationDeadline;


    @Column(name = "your_responsibility", columnDefinition = "TEXT")
    private String yourResponsibility;
    // skills needed
    @Column(name = "skills_needed", columnDefinition = "TEXT")
    private String skillsNeeded;

    // company url
    @Column(name = "company_url")
    private String companyUrl;

    // source
    @Column(name = "application_source")
    private String applicationSource;
    @Column(name = "application_url")
    private String applicationUrl;

    @Column(name = "job_description", columnDefinition = "TEXT")
    private String jobDescription;

    @Column(name = "mail_received")
    private String mailReceived;

    @Column(name = "job_interview_call")
    private String jobInterviewCall;



}
