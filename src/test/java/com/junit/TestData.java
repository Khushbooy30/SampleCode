package com.junit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestData {
	
	@Autowired
	private TestDataRepository testDataRepository;
	
	private void init() {
		testDataRepository.insertData();
	}

}
