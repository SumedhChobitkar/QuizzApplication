package com.OnlineQuizzApplication.OnlineQuizzApplication.Repository;


import com.OnlineQuizzApplication.OnlineQuizzApplication.Entity.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentInfoRepository extends JpaRepository<StudentInfo, Long> {

    boolean existsByemailId(String emailId);
}
