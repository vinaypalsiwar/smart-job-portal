package com.jobportal.backend.dto;

import java.util.List;

public class ATSResult {

    private int atsScore;
    private List<String> matchedSkills;
    private List<String> missingSkills;

    public ATSResult(int atsScore,
            List<String> matchedSkills,
            List<String> missingSkills) {

        this.atsScore = atsScore;
        this.matchedSkills = matchedSkills;
        this.missingSkills = missingSkills;
    }

    public int getAtsScore() {
        return atsScore;
    }

    public List<String> getMatchedSkills() {
        return matchedSkills;
    }

    public List<String> getMissingSkills() {
        return missingSkills;
    }
}
