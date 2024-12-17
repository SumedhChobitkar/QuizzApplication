package com.OnlineQuizzApplication.OnlineQuizzApplication.Controller;



import com.OnlineQuizzApplication.OnlineQuizzApplication.Entity.Question;
import com.OnlineQuizzApplication.OnlineQuizzApplication.Service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    private QuestionService questionService;

    @GetMapping
    public ResponseEntity<List<Question>> getQuestions() {
        logger.info("Fetching all questions");
        List<Question> questions = questionService.getAllQuestions();
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
        logger.info("Fetching question with ID: {}", id);
        Question question = questionService.getQuestionById(id);
        return ResponseEntity.ok(question);
    }

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        logger.info("Creating new question: {}", question.getQuestionText());
        Question savedQuestion = questionService.saveQuestion(question);
        return ResponseEntity.ok(savedQuestion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question question) {
        logger.info("Updating question with ID: {}", id);
        Question updatedQuestion = questionService.updateQuestion(id, question);
        return ResponseEntity.ok(updatedQuestion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long id) {
        logger.info("Deleting question with ID: {}", id);
        questionService.deleteQuestion(id);
        return ResponseEntity.ok("Question deleted successfully.");
    }


}