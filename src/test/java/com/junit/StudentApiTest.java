package com.junit;

import java.net.URI;
import java.util.List;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.junit.controller.StudentController;
import com.junit.entities.Student;
import com.junit.service.StudentService;

import lombok.experimental.Wither;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = StudentController.class)
@Wither
public class StudentApiTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StudentService studentService;

	Student mockstudent= new Student(30l,"Mishka","Patel","mishika.patel","Noida");

	
	@Autowired(required = false)
	String exampleCourseJson ="{\"id\":\"30\",\"firstName\":\"Mishka\",\"lastName\":\"Patel\",\"email\":\"mishika.patel\",\"address\":\"Noida\"}";

	@Test
	public void retrieveDetailsForCourse() throws Exception {

		Mockito.when(studentService.getAllStudents()).thenReturn((List<Student>) mockstudent);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/students/getAll").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{\"id\":\"30\",\"firstName\":\"Mishka\",\"lastName\":\"Patel\",\"email\":\"mishika.patel\",\"address\":\"Noida\"}";


		// {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K Students","steps":["Learn Maven","Import Project","First Example","Second Example"]}

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}

}

