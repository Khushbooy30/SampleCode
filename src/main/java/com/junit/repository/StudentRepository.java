package com.junit.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.junit.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

	public Student findByEmail(String email);
	public Optional<Student> findById(long id);
	public List<Student> findAll();
	public Student save(Student student);
	public void deleteById(long id);

}
