package com.jobportal.backend.repository;

import com.jobportal.backend.model.Resume;
import com.jobportal.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    Optional<Resume> findByUser(User user);
}
