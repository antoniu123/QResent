package com.example.qresent.service;

import com.example.qresent.model.Student_Materie_Prezenta;
import com.example.qresent.repository.OrarRepository;
import com.example.qresent.repository.StudentMateriePrezentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentMateriePrezentaService {
	public final StudentMateriePrezentaRepository studentMateriePrezentaRepository;
	public final OrarRepository orarRepository;
	public final StudentMaterieService studentMaterieService;

	@Autowired
	public StudentMateriePrezentaService(StudentMateriePrezentaRepository studentMateriePrezentaRepository,
										 OrarRepository orarRepository,
										 StudentMaterieService studentMaterieService) {
		this.studentMateriePrezentaRepository = studentMateriePrezentaRepository;
		this.orarRepository = orarRepository;
		this.studentMaterieService = studentMaterieService;
	}

	public void saveStudentMateriePrezenta(Student_Materie_Prezenta student_materie_prezenta){
		studentMateriePrezentaRepository.save(student_materie_prezenta);
	}

	public Optional<Student_Materie_Prezenta> findById(Long id){
		return studentMateriePrezentaRepository.findById(id);
	}

	public void generarePrezenta(Long materieId){
		orarRepository.findByMaterie_Id(materieId).forEach(orar->{
				studentMaterieService.findByMaterieId(materieId).forEach(sm->{
					Student_Materie_Prezenta studentMateriePrezenta = new Student_Materie_Prezenta();
					studentMateriePrezenta.setStudent_Materie(sm);
					studentMateriePrezenta.setPrezenta(0);
					studentMateriePrezenta.setData_curs(orar.getOra());
					studentMateriePrezenta.setUuid(UUID.randomUUID());
					studentMateriePrezenta.setValabilitate(orar.getOra().plus(10L, ChronoUnit.MINUTES));
					studentMateriePrezentaRepository.save(studentMateriePrezenta);
				});
		});
	}
//	public void deletePrezenta(Long materieId, LocalDate zi){
//		studentMateriePrezentaRepository.deleteAllByMaterie_IdAndData_curs(materieId,zi);
//	}
}
