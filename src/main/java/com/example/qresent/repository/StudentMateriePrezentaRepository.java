package com.example.qresent.repository;

import com.example.qresent.model.StudentMateriePrezenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentMateriePrezentaRepository extends JpaRepository <StudentMateriePrezenta, Long> {

	List<StudentMateriePrezenta> findAllByUuid(UUID uuid);

}
