package com.jobportal.backend.controller;

import com.jobportal.backend.model.Resume;
import com.jobportal.backend.model.User;
import com.jobportal.backend.service.ResumeService;
import com.jobportal.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/resumes")
@CrossOrigin(origins = "*")
public class ResumeController {

    private final ResumeService resumeService;
    private final UserService userService;

    public ResumeController(ResumeService resumeService,
            UserService userService) {
        this.resumeService = resumeService;
        this.userService = userService;
    }

    @PostMapping("/upload/{email}")
    public ResponseEntity<?> uploadResume(
            @RequestParam("file") MultipartFile file,
            @PathVariable String email) {

        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("File is empty");
            }

            if (!file.getOriginalFilename().toLowerCase().endsWith(".pdf")) {
                return ResponseEntity.badRequest().body("Only PDF allowed");
            }

            User user = userService.getUserByEmail(email);
            Resume resume = resumeService.uploadResume(file, user);

            return ResponseEntity.ok(resume);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body("Resume upload failed");

        }
    }
}
