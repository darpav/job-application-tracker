package com.drc.jobapplicationtracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
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
    @Column(name = "company_url")
    private String companyUrl;

    @Column(name = "job_title")
    private String jobTitle;
    @Column(name = "job_application_url")
    private String jobApplicationUrl;

    @Column(name = "job_application_source")
    private String jobApplicationSource;
    @Column(name = "job_application_source_url")
    private String jobApplicationSourceUrl;

    @Column(name = "job_application_date")
    private LocalDate jobApplicationDate;
    @Column(name = "job_application_deadline")
    private LocalDate jobApplicationDeadline;

    @Column(name = "job_description", columnDefinition = "TEXT")
    private String jobDescription;

    @Column(name = "job_responsibility", columnDefinition = "TEXT")
    private String jobResponsibility;

    @Column(name = "job_skills", columnDefinition = "TEXT")
    private String jobSkills;

    @Column(name = "confirmation_mail_received")
    private String confirmationMailReceived;

    @Column(name = "job_interview_call")
    private String jobInterviewCall;

    // app user id

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

}
