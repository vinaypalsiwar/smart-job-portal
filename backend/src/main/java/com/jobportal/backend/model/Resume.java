package com.jobportal.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "resumes")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String filePath;

    @Column(length = 2000)
    private String skillsExtracted;

    private LocalDateTime uploadedAt = LocalDateTime.now();

    @OneToOne(optional = false) // 🔥 IMPORTANT
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // getters & setters

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getSkillsExtracted() {
        return skillsExtracted;
    }

    public void setSkillsExtracted(String skillsExtracted) {
        this.skillsExtracted = skillsExtracted;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
