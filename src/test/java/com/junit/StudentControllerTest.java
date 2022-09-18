package com.junit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import com.junit.controller.StudentController;
import com.junit.entities.Student;
import com.junit.repository.StudentRepository;
import com.junit.service.StudentService;

@ExtendWith(SpringExtension.class)
//@RunWith(JUnitPlatform.class)
@SpringBootTest
public class StudentControllerTest {

	@InjectMocks
	StudentController studentController;

	@Mock
	StudentService studentService;

	@Mock
	StudentRepository studentRepository;
	/*
	@MockBean
	private MockMvc mockMvc;
	
	@Test
	void testFindAll_return_status_200() throws Exception{
		MockHttpServletRequestBuilder reqBuilder=MockMvcRequestBuilders.get("/student/getAll");
		ResultActions perform=mockMvc.perform(reqBuilder);
		MvcResult mvcResult=perform.andReturn();
		MockHttpServletResponse response=mvcResult.getResponse();
		assertEquals(200,response.getStatus());
	}
	*/
	@Autowired
	RestTemplate restTemplate;
	HttpHeaders headers;
/*
    @Test
    public void test_getAll_method() throws Exception{
    	String requestJson= IOUtils.toString(
                StudentControllerTest.class.getResourceAsStream("/student.json"), "UTF-8");

        
        HttpEntity<String> entity = new HttpEntity<String>(requestJson);
        ResponseEntity<String> response = restTemplate.exchange("/student/getAll", HttpMethod.GET,
                entity, String.class);
   //     assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
     //   assertNotNull(response.getBody());

    }
    */
	
	@LocalServerPort
	
    private int port;
	
	/*
    @Test
    public void testRetrieveStudentCourse() throws JSONException {

       HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/student/getAll"),
                HttpMethod.GET, entity, String.class);

        String expected = "{\"id\":\20l\",\"firstName\":\"Mishika\",\"lastName\":\"Patel\",\"email\":\"mishika.patel@gmail.com\",\"address\":\"Delhi\"}";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }*/

	@Test
	@DisplayName("Test findAll method for controller")
	public void testFindAllController() {
		// mocking the repository
	    Student student1 = new Student(1, "Ishani", "Sharma", "ishanisharma@gmail.com", "Delhi");
		Student student2 = new Student(1, "Isha", "Gupta", "ishagupta@gmail.com", "Chandigarh");
		List<Student> list = new ArrayList<Student>();
	    list.add(student1);
	    list.add(student2);

	//	when(studentService.findAll()).thenReturn(list);
		ResponseEntity<List<Student>> response = studentController.getAllStudents();
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	    assertEquals(2,list.size());

	}

	@Test
	@DisplayName("Test save method for controller")
	public void testSave() {
//	when(studentService.save(any(Student.class))).thenReturn(true);
		Student student1 = new Student(4, "X", "Y", "Z", "P");
		ResponseEntity<Student> response = studentController.addStudent(student1);
		assertEquals(response.getStatusCode(), HttpStatus.CREATED);

	}

	@Test
	@DisplayName("Test FindById method for controlelr")
	public void testFindById() {
		Student student1 = new Student(3l, "Chirag", "Gupta", "chiraggupta@gmail.com", "Chennai");
		when(studentService.getStudent(3l)).thenReturn(Optional.of(student1));
     	ResponseEntity<Optional<Student>> response=studentController.getStudent(3l);
		assertEquals(response.getStatusCode(),HttpStatus.OK);

	}
	
	@Test
	@DisplayName("Test delete method for controller")
	void testDelete() {
		Student student1 = new Student(3l, "Chirag", "Gupta", "chiraggupta@gmail.com", "Chennai");
	//	when(studentService.delete(3l)).thenReturn(student1);
     	ResponseEntity<Student> response=studentController.deleteStudent(3l);
        assertEquals(response.getStatusCode(),HttpStatus.NO_CONTENT);
	}
	
	
}
