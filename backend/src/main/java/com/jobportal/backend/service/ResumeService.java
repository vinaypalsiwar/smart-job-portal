package com.jobportal.backend.service;

import com.jobportal.backend.model.Resume;
import com.jobportal.backend.model.User;
import com.jobportal.backend.repository.ResumeRepository;
import com.jobportal.backend.util.SkillExtractor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.*;

@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;

    public ResumeService(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    public Resume uploadResume(MultipartFile file, User user) throws Exception {

        if (user == null) {
            throw new RuntimeException("User cannot be null");
        }

        // ✅ SAFE ABSOLUTE PATH (WINDOWS SAFE)
        Path uploadDir = Paths.get(
                System.getProperty("user.home"),
                "SmartJobPortal",
                "uploads",
                "resumes");

        Files.createDirectories(uploadDir);

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = uploadDir.resolve(fileName);

        // ✅ SAFE FILE WRITE
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // ✅ SKILL EXTRACTION (FAIL-SAFE)
        String extractedSkills = "";
        try {
            extractedSkills = SkillExtractor.extractSkillsFromPdf(filePath.toString());
        } catch (Exception e) {
            System.out.println("⚠️ Skill extraction failed, continuing upload");
        }

        Resume resume = new Resume();
        resume.setUser(user);
        resume.setFileName(file.getOriginalFilename());
        resume.setFilePath(filePath.toString());
        resume.setSkillsExtracted(extractedSkills);

        return resumeRepository.save(resume);
    }

    public Resume getResumeByUser(User user) {
        return resumeRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Resume not found"));
    }
}
