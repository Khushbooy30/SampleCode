package com.junit;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TestDataRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Transactional
	public void insertData() {
		try {
			
			jdbcTemplate.update("INSERT INTO studentrecord (id,firstName,lastName,email,address) VALUES(?,?,?,?,?)",100,"A","B","ab@gmail.com","C");
			
		}
		catch(DataAccessException e) {
			e.printStackTrace();
		}
	}

}
