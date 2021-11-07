package com.example.qresent.repository;

import com.example.qresent.model.Student_Materie_Prezenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface StudentMateriePrezentaRepository extends JpaRepository <Student_Materie_Prezenta, Long> {
	@Modifying(clearAutomatically = true)
	@Query("update Student_Materie_Prezenta e set e.prezenta=1 where e.uuid = :uuid")
	int setPrezenta(@Param("uuid")UUID uuid);

}
