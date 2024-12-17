package com.OnlineQuizzApplication.OnlineQuizzApplication.Repository;


import com.OnlineQuizzApplication.OnlineQuizzApplication.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByDomain(String domain);
}
