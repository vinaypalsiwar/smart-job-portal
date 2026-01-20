package com.jobportal.backend.controller;

import com.jobportal.backend.model.Application;
import com.jobportal.backend.model.Job;
import com.jobportal.backend.model.User;
import com.jobportal.backend.service.ApplicationService;
import com.jobportal.backend.service.JobService;
import com.jobportal.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "*")
public class ApplicationController {

    private final ApplicationService applicationService;
    private final JobService jobService;
    private final UserService userService;

    public ApplicationController(ApplicationService applicationService,
            JobService jobService,
            UserService userService) {
        this.applicationService = applicationService;
        this.jobService = jobService;
        this.userService = userService;
    }

    @PostMapping("/apply/{jobId}/{userEmail}")
    public Application applyForJob(@PathVariable Long jobId,
            @PathVariable String userEmail) {

        Job job = jobService.getJobById(jobId);
        User user = userService.getUserByEmail(userEmail);

        return applicationService.applyForJob(job, user);
    }

    // View applications by user
    @GetMapping("/user/{email}")
    public List<Application> getApplicationsByUser(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        return applicationService.getApplicationsByUser(user);
    }
}
