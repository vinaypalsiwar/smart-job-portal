package com.jobportal.backend.controller;

import com.jobportal.backend.dto.ATSResult;
import com.jobportal.backend.dto.CandidateATSResult;
import com.jobportal.backend.model.*;
import com.jobportal.backend.repository.ApplicationRepository;
import com.jobportal.backend.repository.ResumeRepository;
import com.jobportal.backend.service.ATSService;
import com.jobportal.backend.service.JobService;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/recruiter/ats")
@CrossOrigin("*")
public class RecruiterATSController {

    private final ApplicationRepository applicationRepository;
    private final ResumeRepository resumeRepository;
    private final ATSService atsService;
    private final JobService jobService;

    public RecruiterATSController(
            ApplicationRepository applicationRepository,
            ResumeRepository resumeRepository,
            ATSService atsService,
            JobService jobService) {

        this.applicationRepository = applicationRepository;
        this.resumeRepository = resumeRepository;
        this.atsService = atsService;
        this.jobService = jobService;
    }

    @GetMapping("/rank/{jobId}")
    public List<CandidateATSResult> rankCandidates(@PathVariable Long jobId) {

        Job job = jobService.getJobById(jobId);
        List<Application> applications = applicationRepository.findByJob(job);

        return applications.stream()
                .filter(app -> app.getApplicant() != null)
                .map(app -> {

                    Resume resume = resumeRepository
                            .findByUser(app.getApplicant())
                            .orElse(null);

                    if (resume == null)
                        return null;

                    ATSResult ats = atsService.calculateATS(
                            resume.getSkillsExtracted(),
                            job.getSkillsRequired());

                    return new CandidateATSResult(
                            app.getApplicant().getEmail(),
                            ats.getAtsScore());
                })
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(CandidateATSResult::getAtsScore).reversed())
                .collect(Collectors.toList());
    }
}
