package com.OnlineQuizzApplication.OnlineQuizzApplication.Controller;

import com.OnlineQuizzApplication.OnlineQuizzApplication.Entity.QuizSubmission;
import com.OnlineQuizzApplication.OnlineQuizzApplication.Entity.StudentInfo;
import com.OnlineQuizzApplication.OnlineQuizzApplication.Repository.StudentInfoRepository;
import com.OnlineQuizzApplication.OnlineQuizzApplication.Service.QuizSubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/quizSubmission")
public class QuizSubmissionController {

    @Autowired
    private QuizSubmissionService quizService;

    @Autowired
    private StudentInfoRepository studentInfoRepository;

    @PostMapping("/submit")
    public ResponseEntity<?> submitQuiz(

            @PathVariable String domain,
            @RequestBody Map<String, Object> payload) {

//        StudentInfo user = studentInfoRepository.findById(id).orElseThrow(() ->
//                new RuntimeException("User not found"));
      StudentInfo user=new StudentInfo();
        @SuppressWarnings("unchecked")
        Map<String, Map<Long, String>> userAnswers = (Map<String, Map<Long, String>>) payload.get("userAnswers");

        @SuppressWarnings("unchecked")
        Map<String, Integer> stepScores = (Map<String, Integer>) payload.get("stepScores");

        QuizSubmission submission = quizService.submitQuiz(user, domain, userAnswers, stepScores);
        return ResponseEntity.ok(submission);
    }

    @PostMapping("/result-submit")
    public ResponseEntity<QuizSubmission> saveQuizResult(@RequestBody QuizSubmission quizResult) {
        QuizSubmission savedResult = quizService.saveQuizResult(quizResult);
        return ResponseEntity.ok(savedResult);
    }

    @GetMapping("/evaluate")
    public ResponseEntity<String> evaluate(@RequestParam Long userId, @RequestParam String domain) {
        return new ResponseEntity<>(quizService.evaluateUser(userId, domain), HttpStatus.OK);
    }
}
