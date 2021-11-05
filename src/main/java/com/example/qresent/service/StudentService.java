package com.example.qresent.service;

import com.example.qresent.model.Student;
import com.example.qresent.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

	private final StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public List<Student> listAllStudents(){
		return studentRepository.findAll();
	}

	public Student saveStudent(final Student student) {
		return studentRepository.save(student);
	}

	public void deleteStudent(final Long id) {
		studentRepository.delete(studentRepository.findById(id).orElseGet(Student::new));
	}

	public Optional<Student> findById(Long id) {
		return studentRepository.findById(id);
	}
}
