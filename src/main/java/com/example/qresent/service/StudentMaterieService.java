package com.example.qresent.service;

import com.example.qresent.model.Student_Materie;
import com.example.qresent.repository.OrarRepository;
import com.example.qresent.repository.StudentMaterieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentMaterieService {

	private final OrarRepository orarRepository;
	private final StudentMaterieRepository studentMaterieRepository;

	@Autowired
	public StudentMaterieService(OrarRepository orarRepository, StudentMaterieRepository studentMaterieRepository) {
		this.orarRepository = orarRepository;
		this.studentMaterieRepository = studentMaterieRepository;
	}

	public void save(Student_Materie student_materie){
		studentMaterieRepository.save(student_materie);
	}

	public Optional<Student_Materie> findById(Long id){
		return studentMaterieRepository.findById(id);
	}

	public List<Student_Materie> findAll(){
		return studentMaterieRepository.findAll();
	}

	public List<Student_Materie> findByMaterieId(Long id){
		return studentMaterieRepository.findByMaterie_Id(id);
	}

	public List<Student_Materie> findByStudentId(Long id){
		return studentMaterieRepository.findByStudentId(id);
	}

	public void delete(Long id){
		studentMaterieRepository.deleteById(id);
	}
}
