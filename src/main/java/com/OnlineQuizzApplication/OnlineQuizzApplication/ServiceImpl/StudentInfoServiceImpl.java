package com.OnlineQuizzApplication.OnlineQuizzApplication.ServiceImpl;


import com.OnlineQuizzApplication.OnlineQuizzApplication.Entity.StudentInfo;
import com.OnlineQuizzApplication.OnlineQuizzApplication.Repository.StudentInfoRepository;
import com.OnlineQuizzApplication.OnlineQuizzApplication.Service.StudentInfoService;
import com.OnlineQuizzApplication.OnlineQuizzApplication.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentInfoServiceImpl implements StudentInfoService {


    @Autowired
    private final StudentInfoRepository repository;

    public StudentInfoServiceImpl(StudentInfoRepository repository) {
        this.repository = repository;
    }


    public StudentInfo createStudent(StudentInfo student) {
        return repository.save(student);
    }

    public StudentInfo getStudentById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));
    }

    public List<StudentInfo> getAllStudents() {
        return repository.findAll();
    }

    public StudentInfo updateStudent(Long id, StudentInfo updatedStudent) {
        StudentInfo existingStudent = getStudentById(id);
        updatedStudent.setId(existingStudent.getId());
        return repository.save(updatedStudent);
    }

    public void deleteStudent(Long id) {
        StudentInfo student = getStudentById(id);
        repository.delete(student);
    }
}
