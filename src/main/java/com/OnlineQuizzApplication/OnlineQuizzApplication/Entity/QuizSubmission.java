package com.OnlineQuizzApplication.OnlineQuizzApplication.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class QuizSubmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private StudentInfo user;

    private String domain;
    private int totalQuestions;
    private int correctAnswers;
    private boolean isPassed;

    private int aptitudeScore;
    private int logicalScore;
    private int codingScore;

    // Getters and Setters
}
