package com.junit.services;

import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.junit.entities.Student;
import com.junit.repository.StudentRepository;
import com.junit.service.StudentServiceImpl;

@SpringBootTest
class StudentServiceTest {

	@InjectMocks
	StudentServiceImpl studentService;
	
	@MockBean
	StudentRepository studentRepository;
	
	@Test
	@DisplayName("Test findAll method")
	void testFindAll() throws IllegalStateException{
		
		Student student1=new Student(1l,"Ishani","Sharma","ishasharma@gmail.com","Delhi");
		Student student2=new Student(2l,"Isha","Gupta","ishagupta@gmail.com","Chandigarh");
		List<Student> list=new ArrayList<Student>();
		list.add(student1);
		list.add(student2);
		doReturn(list).when(studentRepository).findAll();
		
		List<Student> student=studentService.getAllStudents();
		
		Assertions.assertEquals(2,student.size(),"findAll should return two student data");
	}
}
