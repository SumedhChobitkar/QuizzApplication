package com.OnlineQuizzApplication.OnlineQuizzApplication.Controller;


import com.OnlineQuizzApplication.OnlineQuizzApplication.Entity.Question;
import com.OnlineQuizzApplication.OnlineQuizzApplication.Entity.QuizSubmission;
import com.OnlineQuizzApplication.OnlineQuizzApplication.Entity.StudentInfo;
import com.OnlineQuizzApplication.OnlineQuizzApplication.Repository.StudentInfoRepository;
import com.OnlineQuizzApplication.OnlineQuizzApplication.Service.QuizService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class QuizController {

    private static final Logger logger = LoggerFactory.getLogger(QuizController.class);

    @Autowired
    private QuizService quizService;

    @Autowired
    private StudentInfoRepository userRepository;

    @GetMapping("/quiz/questions")
    public ResponseEntity<?> getQuestions(@RequestParam String domain) {
        try {
            logger.info("Fetching questions for domain: {}", domain);
            List<Question> questions = quizService.getQuestionsByDomain(domain);
            return ResponseEntity.ok(questions);
        } catch (RuntimeException e) {
            logger.error("Error fetching questions for domain: {}", domain, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

//    @PostMapping("/submit")
//    public ResponseEntity<?> submitQuiz(
//            @RequestParam Long userId,
//            @RequestParam String domain,
//            @RequestBody Map<String, Object> payload) {
//
//        try {
//            logger.info("Submitting quiz for userId: {} in domain: {}", userId, domain);
//
//            StudentInfo user = userRepository.findById(userId).orElseThrow(() ->
//                    new RuntimeException("User not found"));
//
//            @SuppressWarnings("unchecked")
//            Map<Long, String> userAnswers = (Map<Long, String>) payload.get("userAnswers");
//
//            @SuppressWarnings("unchecked")
//            Map<String, Integer> stepScores = (Map<String, Integer>) payload.get("stepScores");
//
//            QuizSubmission submission = quizService.submitQuiz(user, domain, userAnswers, stepScores);
//            return ResponseEntity.ok(submission);
//
//        } catch (RuntimeException e) {
//            logger.error("Error submitting quiz for userId: {} in domain: {}", userId, domain, e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//        }
//    }

    @PostMapping("/quiz/submit")
    public ResponseEntity<?> submitQuiz(
            @RequestParam Long studentId, // use studentId instead of userId
            @RequestParam String domain,
            @RequestBody Map<String, Object> payload) {

        try {
            logger.info("Submitting quiz for studentId: {} in domain: {}", studentId, domain);

            // Fetch student info using studentId
            StudentInfo studentInfo = userRepository.findById(studentId)
                    .orElseThrow(() -> new RuntimeException("Student not found"));

            @SuppressWarnings("unchecked")
            Map<Long, String> userAnswers = (Map<Long, String>) payload.get("userAnswers");

            @SuppressWarnings("unchecked")
            Map<String, Integer> stepScores = (Map<String, Integer>) payload.get("stepScores");

            QuizSubmission submission = quizService.submitQuiz(studentInfo, domain, userAnswers, stepScores);
            return ResponseEntity.ok(submission);

        } catch (RuntimeException e) {
            logger.error("Error submitting quiz for studentId: {} in domain: {}", studentId, domain, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}