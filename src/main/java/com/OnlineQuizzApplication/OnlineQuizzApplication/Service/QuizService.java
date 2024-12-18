package com.OnlineQuizzApplication.OnlineQuizzApplication.Service;


import com.OnlineQuizzApplication.OnlineQuizzApplication.Entity.Question;
import com.OnlineQuizzApplication.OnlineQuizzApplication.Entity.QuizSubmission;
import com.OnlineQuizzApplication.OnlineQuizzApplication.Entity.StudentInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface QuizService {

    public List<Question> getQuestionsByDomain(String domain);

    public QuizSubmission submitQuiz(
            StudentInfo user,
            String domain,
            Map<Long, String> userAnswers,
            Map<String, Integer> stepScores);
}