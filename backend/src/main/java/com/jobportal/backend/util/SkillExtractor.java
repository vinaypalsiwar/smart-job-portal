package com.jobportal.backend.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.util.*;

public class SkillExtractor {

    // Common skills list (can expand)
    private static final List<String> SKILLS = Arrays.asList(
            "java", "spring", "spring boot", "hibernate",
            "react", "angular", "javascript",
            "python", "django", "flask",
            "sql", "mysql", "postgresql",
            "mongodb", "docker", "kubernetes",
            "aws", "git", "rest api");

    public static String extractSkillsFromPdf(String filePath) {

        Set<String> foundSkills = new HashSet<>();

        try (PDDocument document = PDDocument.load(new File(filePath))) {

            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document).toLowerCase();

            for (String skill : SKILLS) {
                if (text.contains(skill)) {
                    foundSkills.add(skill.toUpperCase());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return String.join(", ", foundSkills);
    }
}
