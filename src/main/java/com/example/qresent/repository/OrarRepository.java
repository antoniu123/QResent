package com.example.qresent.repository;

import com.example.qresent.model.Materie;
import com.example.qresent.model.Orar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrarRepository extends JpaRepository <Orar,Long> {
	List<Orar> findAllByMaterieAndZi(Materie materie, LocalDate zi);
}
