package com.junit;



import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.junit.entities.Student;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class StudentTest {
	
	

	private TestRestTemplate restTemplate;
	
	@Test
	public void StudentTest() throws NullPointerException{
		String requestJson =new String(Student.class.getResourceAsStream("/studentdata.json"));
		HttpHeaders headers=new HttpHeaders();
		headers.add("Content-Type","application/json");
		headers.add("Accept","application/json");
		HttpEntity<String> requestGet = new HttpEntity<String>(headers);
		
		String url="http://localhost:8080/student/{1}";
		
		ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.GET, requestGet,String.class);
		assertEquals(HttpStatus.OK,response.getStatusCode());
		
	}
	
	@Test
	  public void testHello() throws NullPointerException{
	    ResponseEntity<String> respEntity = restTemplate.getForEntity("/student", String.class);
	    assertThat(respEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
	   
	  }
	
	

}
