package com.jobportal.backend.service;

import com.jobportal.backend.dto.ATSResult;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ATSService {

    public ATSResult calculateATS(String resumeSkills, String jobSkills) {

        if (resumeSkills == null || resumeSkills.isBlank()) {
            return new ATSResult(0, List.of(), List.of());
        }

        if (jobSkills == null || jobSkills.isBlank()) {
            return new ATSResult(0, List.of(), List.of());
        }

        Set<String> resumeSet = Arrays.stream(resumeSkills.split(","))
                .map(String::trim)
                .map(String::toUpperCase)
                .collect(Collectors.toSet());

        Set<String> jobSet = Arrays.stream(jobSkills.split(","))
                .map(String::trim)
                .map(String::toUpperCase)
                .collect(Collectors.toSet());

        List<String> matched = resumeSet.stream()
                .filter(jobSet::contains)
                .toList();

        List<String> missing = jobSet.stream()
                .filter(s -> !resumeSet.contains(s))
                .toList();

        int score = (int) (((double) matched.size() / jobSet.size()) * 100);

        return new ATSResult(score, matched, missing);
    }
}
