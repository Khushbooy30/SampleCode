package com.junit.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.junit.entities.Student;
import com.junit.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	public StudentController() {

	}

	@PostMapping(path = "/student", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Student> addStudent(@RequestBody Student student) {
		try {
			return new ResponseEntity<Student>(studentService.addStudent(student), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Student>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(path = "/student", produces = "application/json")
	public ResponseEntity<List<Student>> getAllStudents() {

		try {
			List<Student> list = studentService.getAllStudents();
			return new ResponseEntity<List<Student>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Student>>(HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping(path = "student/{id}", produces = "application/json")
	public ResponseEntity<Optional<Student>> getStudent(@PathVariable("id") long id) {

		try {
			Optional<Student> student = studentService.getStudent(id);
			return new ResponseEntity<Optional<Student>>(student,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Optional<Student>>(HttpStatus.NOT_FOUND);
		}
		
	}

	@PutMapping(path = "student/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Student> updateStudent(@PathVariable("id") long id, @RequestBody Student student) {
		try {
			Student student1 = studentService.updateStudent(id, student);
			return new ResponseEntity<>(student1, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/student/{id}")
	public ResponseEntity<Student> deleteStudent(@PathVariable("id") long id) {
		studentService.deleteStudent(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

}