package com.jobportal.backend.repository;

import com.jobportal.backend.model.Job;
import com.jobportal.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findByRecruiter(User recruiter);

    List<Job> findByLocation(String location);
}
