package com.example.qresent.repository;

import com.example.qresent.model.StudentMaterie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMaterieRepository extends JpaRepository <StudentMaterie,Long> {
	List<StudentMaterie> findByMaterie_Id(Long materieId);
	List<StudentMaterie> findByStudentId(Long studentId);
}
