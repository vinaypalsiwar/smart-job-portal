package com.jobportal.backend.controller;

import com.jobportal.backend.dto.ATSResult;
import com.jobportal.backend.model.Job;
import com.jobportal.backend.model.Resume;
import com.jobportal.backend.model.User;
import com.jobportal.backend.service.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ats")
@CrossOrigin(origins = "*")
public class ATSController {

    private final ATSService atsService;
    private final JobService jobService;
    private final ResumeService resumeService;
    private final UserService userService;

    public ATSController(
            ATSService atsService,
            JobService jobService,
            ResumeService resumeService,
            UserService userService) {

        this.atsService = atsService;
        this.jobService = jobService;
        this.resumeService = resumeService;
        this.userService = userService;
    }

    @GetMapping("/score/{jobId}/{email}")
    public ATSResult getATSScore(
            @PathVariable Long jobId,
            @PathVariable String email) {

        Job job = jobService.getJobById(jobId);
        User user = userService.getUserByEmail(email);
        Resume resume = resumeService.getResumeByUser(user);

        return atsService.calculateATS(
                resume.getSkillsExtracted(),
                job.getSkillsRequired());
    }
}
