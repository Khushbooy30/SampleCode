package com.junit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.exceptions.misusing.UnfinishedStubbingException;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.junit.entities.Student;
import com.junit.repository.StudentRepository;
import com.junit.service.StudentServiceImpl;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
class SpringBootRestApiJunitApplicationTests {
	@InjectMocks
	StudentServiceImpl studentService;
	
	@MockBean
	StudentRepository studentRepository;
	
	
	@Test
	@DisplayName("Test findAll method for Service")
	void testFindAll() throws IllegalStateException{
		
		Student student1=new Student(1l,"Ishani","Sharma","ishasharma@gmail.com","Delhi");
		Student student2=new Student(2l,"Isha","Gupta","ishagupta@gmail.com","Chandigarh");
		List<Student> list=new ArrayList<Student>();
		list.add(student1);
		list.add(student2);
		doReturn(list).when(studentRepository).findAll();
		
		List<Student> student=studentService.getAllStudents();
		
		Assertions.assertEquals(2,student.size());
	}
	
	@Test
	@DisplayName("Test FindById method for service")
	public void testFindById() {
		Student student1=new Student(3l,"Chirag","Gupta","chiraggupta@gmail.com","Chennai");
		when(studentRepository.findById(3l)).thenReturn(Optional.of(student1));
		
		Optional<Student> result=studentService.getStudent(3l);
		
		Assertions.assertTrue(result.isPresent());
		
	}

	@Test
	@DisplayName("Test FindByIdNotFound method for service")
	void testFindByIdNotFound() {
		doReturn (Optional.empty()).when(studentRepository.findById(1l));
		
		Optional<Student> result=studentService.getStudent(1l);
		
		Assertions.assertTrue(result.isPresent());
	}
	
	@Test
	@DisplayName("Test save method for service")
	void testSave() throws UnfinishedStubbingException {
		Student student1=new Student(1l,"A","B","C","D");
		doReturn(student1).when(studentRepository.save(student1));
		Student result=studentService.addStudent(student1);
		
		Assertions.assertNotNull(result);
		

	}
}
