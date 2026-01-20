package com.jobportal.backend.controller;

import com.jobportal.backend.model.User;
import com.jobportal.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Register Job Seeker
    @PostMapping("/register/jobseeker")
    public ResponseEntity<User> registerJobSeeker(@RequestBody User user) {
        User savedUser = userService.registerUser(user, "JOB_SEEKER");
        return ResponseEntity.ok(savedUser);
    }

    // Register Recruiter
    @PostMapping("/register/recruiter")
    public ResponseEntity<User> registerRecruiter(@RequestBody User user) {
        User savedUser = userService.registerUser(user, "RECRUITER");
        return ResponseEntity.ok(savedUser);
    }
}
