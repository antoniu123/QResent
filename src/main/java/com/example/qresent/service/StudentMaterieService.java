package com.example.qresent.service;

import com.example.qresent.model.Materie;
import com.example.qresent.model.StudentMaterie;
import com.example.qresent.repository.StudentMaterieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentMaterieService {

	private final StudentMaterieRepository studentMaterieRepository;


	@Autowired
	public StudentMaterieService(final StudentMaterieRepository studentMaterieRepository) {
		this.studentMaterieRepository = studentMaterieRepository;
	}

	public void save(StudentMaterie student_materie) {
		studentMaterieRepository.save(student_materie);
	}

	public Optional<StudentMaterie> findById(Long id) {
		return studentMaterieRepository.findById(id);
	}

	public List<StudentMaterie> findAll() {
		return studentMaterieRepository.findAll();
	}

	public List<StudentMaterie> findByMaterieId(Long id) {
		return studentMaterieRepository.findByMaterie_Id(id);
	}

	public List<StudentMaterie> findByStudentId(Long id) {
		return studentMaterieRepository.findByStudentId(id);
	}

	public void delete(Long id) {
		studentMaterieRepository.deleteById(id);
	}

	public List<Materie> findAllMaterieByStudent(Long studentId) {
		return studentMaterieRepository.findByStudentId(studentId).stream()
				.map(StudentMaterie::getMaterie)
				.distinct()
				.collect(Collectors.toList());
	}
}
