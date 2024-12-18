package com.OnlineQuizzApplication.OnlineQuizzApplication.Controller;


import com.OnlineQuizzApplication.OnlineQuizzApplication.Entity.StudentInfo;
import com.OnlineQuizzApplication.OnlineQuizzApplication.Service.StudentInfoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class StudentInfoController {

    private final StudentInfoService service;

    public StudentInfoController(StudentInfoService service) {
        this.service = service;
    }

    @PostMapping("/students")
    public ResponseEntity<StudentInfo> createStudent(@Valid @RequestBody StudentInfo student) {
        return new ResponseEntity<>(service.createStudent(student), HttpStatus.CREATED);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentInfo> getStudentById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getStudentById(id), HttpStatus.OK);
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentInfo>> getAllStudents() {
        return new ResponseEntity<>(service.getAllStudents(), HttpStatus.OK);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<StudentInfo> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentInfo student) {
        return new ResponseEntity<>(service.updateStudent(id, student), HttpStatus.OK);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        service.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
