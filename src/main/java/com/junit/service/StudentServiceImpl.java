package com.junit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.junit.entities.Student;
import com.junit.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	private StudentRepository studentRepository;
	
	
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

	@Override
	public Student addStudent(Student student) {
			return studentRepository.save(student);
		
	}

	@Override
	
	public List<Student> getAllStudents() {
		List<Student> list=studentRepository.findAll();
			return list;
	}

	@Override
	public Optional<Student> getStudent(long id) {
		
			Optional<Student> s= studentRepository.findById(id);
			return s;
		
	}

	@Override
	public Student updateStudent(long id,Student student) {
		
		/*
		 * if( studentRepository.findById(id).isPresent()) {
		 * student.setFirstName(student.getFirstName());
		 * student.setLastName(student.getLastName());
		 * student.setEmail(student.getEmail());
		 * student.setAddress(student.getAddress());
		 * 
		 * }
		 * 
		 */
		return null;
	}

	@Override
	public void deleteStudent(long id) {
			 studentRepository.deleteById(id);
		
	}
}
