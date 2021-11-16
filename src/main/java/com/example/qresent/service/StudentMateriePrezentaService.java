package com.example.qresent.service;

import com.example.qresent.model.Orar;
import com.example.qresent.model.StudentMaterie;
import com.example.qresent.model.StudentMateriePrezenta;
import com.example.qresent.repository.MaterieRepository;
import com.example.qresent.repository.OrarRepository;
import com.example.qresent.repository.StudentMateriePrezentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentMateriePrezentaService {
	private final StudentMateriePrezentaRepository studentMateriePrezentaRepository;
	private final OrarRepository orarRepository;
	private final MaterieRepository materieRepository;
	private final StudentMaterieService studentMaterieService;

	@Autowired
	public StudentMateriePrezentaService(StudentMateriePrezentaRepository studentMateriePrezentaRepository,
										 OrarRepository orarRepository,
										 MaterieRepository materieRepository,
										 StudentMaterieService studentMaterieService) {
		this.studentMateriePrezentaRepository = studentMateriePrezentaRepository;
		this.orarRepository = orarRepository;
		this.materieRepository = materieRepository;
		this.studentMaterieService = studentMaterieService;
	}

	public void saveStudentMateriePrezenta(StudentMateriePrezenta student_materie_prezenta){
		studentMateriePrezentaRepository.save(student_materie_prezenta);
	}

	public Optional<StudentMateriePrezenta> findById(Long id){
		return studentMateriePrezentaRepository.findById(id);
	}

	public List<StudentMateriePrezenta> findAll(){
		return studentMateriePrezentaRepository.findAll();
	}

	public List<StudentMateriePrezenta> findAllActiveByStudentId(Long studentId){
		List<StudentMaterie> listOfStudentMaterie = studentMaterieService.findByStudentId(studentId);
		return studentMateriePrezentaRepository.findAll().stream()
						.filter(smp->listOfStudentMaterie.contains(smp.getStudentMaterie()))
				        .filter(smp->smp.getPrezenta().equals(0))
				        .filter(smp->smp.getValabilitate().isAfter(LocalDateTime.now()))
						.collect(Collectors.toList());
	}

	public List<StudentMateriePrezenta> findAllByStudentId(Long studentId){
		List<StudentMaterie> listOfStudentMaterie = studentMaterieService.findByStudentId(studentId);
		return studentMateriePrezentaRepository.findAll().stream()
				.filter(smp->listOfStudentMaterie.contains(smp.getStudentMaterie()))
				.collect(Collectors.toList());
	}

	public List<StudentMateriePrezenta> findAllByMaterieId(Long materieId){
		List<StudentMaterie> listOfStudentMaterie = studentMaterieService.findByMaterieId(materieId);
		return studentMateriePrezentaRepository.findAll().stream()
				.filter(smp->listOfStudentMaterie.contains(smp.getStudentMaterie()))
				.collect(Collectors.toList());
	}

	public void generarePrezenta(Long orarId){
		Orar orar = orarRepository.findById(orarId).orElseGet(Orar::new);

		if (Objects.nonNull(orar.getMaterie())){
			studentMaterieService.findByMaterieId(orar.getMaterie().getId())
					.forEach(sm->{
						StudentMateriePrezenta studentMateriePrezenta = new StudentMateriePrezenta();
						studentMateriePrezenta.setStudentMaterie(sm);
						studentMateriePrezenta.setPrezenta(0);
						LocalDateTime now = LocalDateTime.now();
						studentMateriePrezenta.setDataCurs(now);
						studentMateriePrezenta.setUuid(UUID.randomUUID());
						studentMateriePrezenta.setValabilitate(now.plus(10L, ChronoUnit.MINUTES));
						studentMateriePrezentaRepository.save(studentMateriePrezenta);
					});
		}
	}

	public void setPrezenta(UUID uuid){
		List<StudentMateriePrezenta> studentMateriePrezentaList = studentMateriePrezentaRepository.findAllByUuid(uuid);
		if (studentMateriePrezentaList.isEmpty())
			throw new RuntimeException("valoare UUID incorecta");
		//list can have only a single element
		if (LocalDateTime.now().isAfter(studentMateriePrezentaList.get(0).getValabilitate()))
			throw new RuntimeException("data limita depasita");
		StudentMateriePrezenta studentMateriePrezenta = studentMateriePrezentaList.get(0);
		studentMateriePrezenta.setPrezenta(1);
		studentMateriePrezentaRepository.save(studentMateriePrezenta);
	}
}
