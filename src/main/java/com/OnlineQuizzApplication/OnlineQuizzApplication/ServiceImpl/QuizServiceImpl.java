package com.OnlineQuizzApplication.OnlineQuizzApplication.ServiceImpl;

import com.OnlineQuizzApplication.OnlineQuizzApplication.Entity.Question;
import com.OnlineQuizzApplication.OnlineQuizzApplication.Entity.QuizSubmission;
import com.OnlineQuizzApplication.OnlineQuizzApplication.Entity.StudentInfo;
import com.OnlineQuizzApplication.OnlineQuizzApplication.Repository.QuestionRepository;
import com.OnlineQuizzApplication.OnlineQuizzApplication.Repository.QuizSubmissionRepository;
import com.OnlineQuizzApplication.OnlineQuizzApplication.Service.QuizService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class QuizServiceImpl implements QuizService {

    private static final Logger logger = LoggerFactory.getLogger(QuizService.class);

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizSubmissionRepository quizSubmissionRepository;

    public List<Question> getQuestionsByDomain(String domain) {
        try {
            logger.info("Fetching questions for domain: {}", domain);
            return questionRepository.findByDomain(domain);
        } catch (Exception e) {
            logger.error("Error fetching questions for domain: {}", domain, e);
            throw new RuntimeException("Failed to fetch questions. Please try again.");
        }
    }

    public QuizSubmission submitQuiz(
            StudentInfo user,
            String domain,
            Map<Long, String> userAnswers,
            Map<String, Integer> stepScores) {

        try {
            List<Question> questions = questionRepository.findByDomain(domain);
            int totalQuestions = questions.size();
            int correctAnswers = 0;

            for (Question question : questions) {
                String correctAnswer = question.getCorrectAnswer();
                String userAnswer = userAnswers.get(question.getId());
                if (correctAnswer.equalsIgnoreCase(userAnswer)) {
                    correctAnswers++;
                }
            }

            boolean isPassed = ((double) correctAnswers / totalQuestions) >= 0.6;

            QuizSubmission submission = new QuizSubmission();
            submission.setUser(user);
            submission.setDomain(domain);
            submission.setTotalQuestions(totalQuestions);
            submission.setCorrectAnswers(correctAnswers);
            submission.setPassed(isPassed);


            submission.setAptitudeScore(stepScores.getOrDefault("aptitude", 0));
            submission.setLogicalScore(stepScores.getOrDefault("logical", 0));
            submission.setCodingScore(stepScores.getOrDefault("coding", 0));

            logger.info("Quiz submitted for user: {}. Domain: {}, Passed: {}", user.getName(), domain, isPassed);
            return quizSubmissionRepository.save(submission);

        } catch (Exception e) {
            logger.error("Error submitting quiz for user: {} in domain: {}", user.getName(), domain, e);
            throw new RuntimeException("Failed to submit quiz. Please try again.");
        }
    }
}
