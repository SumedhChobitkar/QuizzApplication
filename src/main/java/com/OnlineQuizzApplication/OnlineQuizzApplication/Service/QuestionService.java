package com.OnlineQuizzApplication.OnlineQuizzApplication.Service;


import com.OnlineQuizzApplication.OnlineQuizzApplication.Entity.Question;
import com.OnlineQuizzApplication.OnlineQuizzApplication.Repository.QuestionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    private static final Logger logger = LoggerFactory.getLogger(QuestionService.class);


    public List<Question> getAllQuestions() {
        logger.info("Fetching all questions from the database");
        return questionRepository.findAll();
    }

    public Question getQuestionById(Long id) {
        logger.info("Fetching question with ID: {}", id);
        return questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found with ID: " + id));
    }

    public Question saveQuestion(Question question) {
        logger.info("Saving new question: {}", question.getQuestionText());
        return questionRepository.save(question);
    }

    public Question updateQuestion(Long id, Question questionDetails) {
        logger.info("Updating question with ID: {}", id);
        Question existingQuestion = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found with ID: " + id));

        existingQuestion.setQuestionText(questionDetails.getQuestionText());
        existingQuestion.setOptions(questionDetails.getOptions());
        existingQuestion.setCorrectAnswer(questionDetails.getCorrectAnswer());
        existingQuestion.setDomain(questionDetails.getDomain());

        return questionRepository.save(existingQuestion);
    }

    public void deleteQuestion(Long id) {
        logger.info("Deleting question with ID: {}", id);
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found with ID: " + id));
        questionRepository.delete(question);
    }
}