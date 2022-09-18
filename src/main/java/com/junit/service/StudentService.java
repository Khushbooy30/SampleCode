package com.junit.service;

import java.util.List;
import java.util.Optional;

import com.junit.entities.Student;

public interface StudentService {

	
    List<Student> getAllStudents();
    Optional<Student> getStudent(long id);
    void deleteStudent(long id);
	Student updateStudent(long id, Student student);
	Student addStudent(Student student);

	
}
