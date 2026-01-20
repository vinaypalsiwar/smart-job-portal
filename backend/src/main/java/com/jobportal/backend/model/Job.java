package com.jobportal.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 2000)
    private String description;

    // comma separated skills
    private String skillsRequired;

    private String experienceLevel;

    private String location;

    @ManyToOne
    @JoinColumn(name = "recruiter_id")
    private User recruiter;

    private LocalDateTime postedAt = LocalDateTime.now();

    // ===== GETTERS & SETTERS =====

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSkillsRequired() {
        return skillsRequired;
    }

    public void setSkillsRequired(String skillsRequired) {
        this.skillsRequired = skillsRequired;
    }

    public void setRecruiter(User recruiter) {
        this.recruiter = recruiter;
    }
}
