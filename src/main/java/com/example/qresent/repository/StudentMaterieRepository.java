package com.example.qresent.repository;

import com.example.qresent.model.Student_Materie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMaterieRepository extends JpaRepository <Student_Materie,Long> {
	List<Student_Materie> findByMaterie_Id(Long materieId);
	List<Student_Materie> findByStudentId(Long studentId);
}
