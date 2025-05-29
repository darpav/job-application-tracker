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

    @Column(name = "job_title")
    private String jobTitle;

    // job application url -- mandatory for duplicate records
    @Column(name = "job_application_url")
    private String jobApplicationUrl;

    @Column(name = "company_name")
    private String companyName;
    @Column(name = "company_url")
    private String companyUrl;

    @Column(name = "job_application_date")
    private LocalDate jobApplicationDate;
    @Column(name = "job_application_deadline")
    private LocalDate jobApplicationDeadline;

    @Column(name = "job_description", columnDefinition = "TEXT")
    private String jobDescription;

    @Column(name = "job_skills", columnDefinition = "TEXT")
    private String jobSkills;

    @Column(name = "confirmation_mail_received")
    private String confirmationMailReceived;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;


    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private JobApplicationStage jobApplicationStage;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

}
