package com.OnlineQuizzApplication.OnlineQuizzApplication.Repository;

import com.OnlineQuizzApplication.OnlineQuizzApplication.Entity.QuizSubmission;
import com.OnlineQuizzApplication.OnlineQuizzApplication.Entity.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizSubmissionRepository extends JpaRepository<QuizSubmission, Long> {


    List<QuizSubmission> findByUser(StudentInfo user);


    List<QuizSubmission> findByDomain(String domain);


    List<QuizSubmission> findByUserAndDomain(StudentInfo user, String domain);
}
