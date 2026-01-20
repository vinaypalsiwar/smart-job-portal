package com.jobportal.backend.controller;

import com.jobportal.backend.model.Job;
import com.jobportal.backend.model.User;
import com.jobportal.backend.service.JobService;
import com.jobportal.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "*")
public class JobController {

    private final JobService jobService;
    private final UserService userService;

    public JobController(JobService jobService, UserService userService) {
        this.jobService = jobService;
        this.userService = userService;
    }

    // ✅ GET ALL JOBS
    @GetMapping("/all")
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    // ✅ POST JOB (SAFE – NO 500)
    @PostMapping("/post/{recruiterEmail}")
    public ResponseEntity<?> postJob(
            @RequestBody Job job,
            @PathVariable String recruiterEmail) {

        User recruiter = userService.getUserByEmail(recruiterEmail);

        if (recruiter == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Recruiter not found: " + recruiterEmail);
        }

        Job savedJob = jobService.postJob(job, recruiter);
        return ResponseEntity.ok(savedJob);
    }
}
