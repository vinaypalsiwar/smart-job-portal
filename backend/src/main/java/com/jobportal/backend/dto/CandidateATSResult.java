package com.jobportal.backend.dto;

public class CandidateATSResult {

    private String candidateEmail;
    private int atsScore;

    public CandidateATSResult(String candidateEmail, int atsScore) {
        this.candidateEmail = candidateEmail;
        this.atsScore = atsScore;
    }

    public String getCandidateEmail() {
        return candidateEmail;
    }

    public int getAtsScore() {
        return atsScore;
    }
}
