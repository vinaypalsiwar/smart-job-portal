package com.jobportal.backend.service;

import com.jobportal.backend.model.Application;
import com.jobportal.backend.model.Job;
import com.jobportal.backend.model.User;
import com.jobportal.backend.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public Application applyForJob(Job job, User user) {

        Application application = new Application();
        application.setJob(job);
        application.setApplicant(user);
        application.setStatus("APPLIED");

        return applicationRepository.save(application);
    }

    public List<Application> getApplicationsByUser(User user) {
        return applicationRepository.findByApplicant(user);
    }

    public List<Application> getApplicationsByJob(Job job) {
        return applicationRepository.findByJob(job);
    }
}
